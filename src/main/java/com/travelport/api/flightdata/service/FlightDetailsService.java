package com.travelport.api.flightdata.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelport.api.flightdata.aspectj.LogInputOutput;
import com.travelport.api.flightdata.exception.DataRetrievalException;
import com.travelport.api.flightdata.exception.XmlConversionException;
import com.travelport.api.flightdata.model.requiredModel.ArrivesOn;
import com.travelport.api.flightdata.model.requiredModel.Availability;
import com.travelport.api.flightdata.model.requiredModel.BookingFee;
import com.travelport.api.flightdata.model.requiredModel.Business;
import com.travelport.api.flightdata.model.requiredModel.DepartsOn;
import com.travelport.api.flightdata.model.requiredModel.Economy;
import com.travelport.api.flightdata.model.requiredModel.FarePrices;
import com.travelport.api.flightdata.model.requiredModel.First;
import com.travelport.api.flightdata.model.requiredModel.Flight;
import com.travelport.api.flightdata.model.requiredModel.Root;
import com.travelport.api.flightdata.model.requiredModel.Tax;
import com.travelport.api.flightdata.model.requiredModel.Ticket;
import com.travelport.api.flightdata.model.xmlModel.AvailabilityXmlModel;
import com.travelport.api.flightdata.model.xmlModel.FareXmlModel;
import com.travelport.api.flightdata.model.xmlModel.FlightXmlModel;
import com.travelport.api.flightdata.utils.Constant;
import com.travelport.api.flightdata.utils.Utility;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlightDetailsService {

  RestTemplate restTemplate;

  public FlightDetailsService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @LogInputOutput
  public String getFlights(String origin, String destination, String startDate, String endDate,
      Integer noOfPassengers) throws JAXBException, JsonProcessingException {
    String availabilityString = Constant.EMPTY;
    String xml = getXml(origin, destination, startDate, endDate, noOfPassengers);
    if (xml != null || !xml.isEmpty()) {
      try {
        availabilityString = getFinalString(xml);
      } catch (Exception ex) {
        throw new XmlConversionException("Error Occurred During XMl to POJO object conversion");
      }
    }
    return availabilityString;
  }

  @LogInputOutput
  private String getXml(String origin, String destination, String startDate, String endDate,
      Integer noOfPassengers) {
    Map<String, Object> uriParam = new HashMap<>();
    uriParam.put(Constant.ORIGIN, origin);
    uriParam.put(Constant.DESTINATION, destination);
    uriParam.put(Constant.START_DATE, startDate);
    uriParam.put(Constant.END_DATE, endDate);
    uriParam.put(Constant.NO_OF_PASSENGERS, noOfPassengers);

    HttpHeaders httpHeaders = new HttpHeaders();
    HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
    ResponseEntity<String> xml = restTemplate
        .exchange(Constant.URI, HttpMethod.GET, httpEntity, String.class, uriParam);
    if (xml.getBody() == null || xml.getBody().isEmpty()) {
      throw new DataRetrievalException(
          "Error Occurred during data retrieval of Flight XML data from External API");
    }
    return xml.getBody();
  }

  @LogInputOutput
  private String getFinalString(String xml) throws JAXBException, JsonProcessingException {
    String availabilityString;
    ObjectMapper objectMapper = new ObjectMapper();
    Availability availability = prepareAndGetAvailability(parseXmlToAvailability(xml));
    Root root = getRoot(availability);
    availabilityString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
    return availabilityString;
  }

  @LogInputOutput
  private Availability prepareAndGetAvailability(AvailabilityXmlModel availabilityXmlModel) {
    Availability availability = new Availability();
    List<Flight> flights = new ArrayList<>();
    for (FlightXmlModel flightXmlModel : availabilityXmlModel.getFlightXmlModel()) {
      Flight flight = prepareAndGetFlight(flightXmlModel);
      flights.add(flight);
    }

    availability.setFlight(flights);
    return availability;
  }

  @LogInputOutput
  private Flight prepareAndGetFlight(FlightXmlModel flightXmlModel) {
    Flight flight = new Flight();
    flight.setOperator(flightXmlModel.getCarrierCode());
    flight.setFlightNumber(flightXmlModel.getFlightDesignator());
    flight.setDepartsFrom(flightXmlModel.getOriginAirport());
    flight.setArrivesAt(flightXmlModel.getDestinationAirport());
    DepartsOn departsOn = getDepartsOn(flightXmlModel);
    flight.setDepartsOn(departsOn);
    ArrivesOn arrivesOn = getArrivesOn(flightXmlModel);
    flight.setArrivesOn(arrivesOn);
    flight.setFlightTime(Utility.calculateTimeDifference(flightXmlModel.getDepartureDate(),
        flightXmlModel.getArrivalDate()));
    FarePrices farePrices = getFarePrices(flightXmlModel);
    flight.setFarePrices(farePrices);
    return flight;
  }

  @LogInputOutput
  private DepartsOn getDepartsOn(FlightXmlModel flightXmlModel) {
    DepartsOn departsOn = new DepartsOn();
    departsOn.setDate(Utility.getDateString(flightXmlModel.getDepartureDate()));
    departsOn.setTime(Utility.getTimeString(flightXmlModel.getDepartureDate()));
    return departsOn;
  }


  @LogInputOutput
  private ArrivesOn getArrivesOn(FlightXmlModel flightXmlModel) {
    ArrivesOn arrivesOn = new ArrivesOn();
    arrivesOn.setDate(Utility.getDateString(flightXmlModel.getArrivalDate()));
    arrivesOn.setTime(Utility.getTimeString(flightXmlModel.getArrivalDate()));
    return arrivesOn;
  }

  @LogInputOutput
  private FarePrices getFarePrices(FlightXmlModel flightXmlModel) {
    FarePrices farePrices = new FarePrices();
    for (FareXmlModel fareXmlModel : flightXmlModel.getFaresXmlModel().getFareXmlModel()) {
      Ticket ticket = getTicket(fareXmlModel);
      BookingFee bookingFee = getBookingFee(fareXmlModel);
      Tax tax = getTax(fareXmlModel);
      if (fareXmlModel.getFareClass().equalsIgnoreCase("FIF")) {
        First first = new First(ticket, bookingFee, tax);
        farePrices.setFirst(first);
      }
      if (fareXmlModel.getFareClass().equalsIgnoreCase("CIF")) {
        Business business = new Business(ticket, bookingFee, tax);
        farePrices.setBusiness(business);
      }
      if (fareXmlModel.getFareClass().equalsIgnoreCase("YIF")) {
        Economy economy = new Economy(ticket, bookingFee, tax);
        farePrices.setEconomy(economy);
      }
    }
    return farePrices;
  }

  @LogInputOutput
  private Ticket getTicket(FareXmlModel fareXmlModel) {
    Ticket ticket = new Ticket();
    String ticketP = fareXmlModel.getBasePrice();
    ticket.setCurrency(ticketP.substring(0, 2));
    ticket.setAmount(Double.parseDouble(ticketP.substring(4, ticketP.length() - 1)));
    return ticket;
  }

  @LogInputOutput
  private BookingFee getBookingFee(FareXmlModel fareXmlModel) {
    BookingFee bookingFee = new BookingFee();
    String fees = fareXmlModel.getFees();
    bookingFee.setCurrency(fees.substring(0, 2));
    bookingFee.setAmount(Double.parseDouble(fees.substring(4, fees.length() - 1)));
    return bookingFee;
  }


  @LogInputOutput
  private Tax getTax(FareXmlModel fareXmlModel) {
    Tax tax = new Tax();
    String taxM = fareXmlModel.getTax();
    tax.setCurrency(taxM.substring(0, 2));
    tax.setAmount(Double.parseDouble(taxM.substring(4, taxM.length() - 1)));
    return tax;
  }

  @LogInputOutput
  private Root getRoot(Availability availability) {
    List<Availability> availabilityList = new ArrayList<>();
    availabilityList.add(availability);
    Root root = new Root();
    root.setAvailability(availabilityList);
    return root;
  }

  @LogInputOutput
  private AvailabilityXmlModel parseXmlToAvailability(String xml)
      throws JAXBException, JsonProcessingException {
    JAXBContext context = JAXBContext.newInstance(AvailabilityXmlModel.class);
    Unmarshaller unmarshaller = context.createUnmarshaller();
    AvailabilityXmlModel availabilityXmlModel = (AvailabilityXmlModel) unmarshaller
        .unmarshal(new StringReader(xml));
    return availabilityXmlModel;
  }


}
