package com.travelport.api.flightdata.model.xmlModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "Flight")
@XmlAccessorType(XmlAccessType.FIELD)
public class FlightXmlModel {

  @XmlElement(name = "CarrierCode")
  private String carrierCode;

  @XmlElement(name = "FlightDesignator")
  private String flightDesignator;

  @XmlElement(name = "OriginAirport")
  private String originAirport;

  @XmlElement(name = "DestinationAirport")
  private String destinationAirport;

  @XmlElement(name = "DepartureDate")
  private XMLGregorianCalendar departureDate;

  @XmlElement(name = "ArrivalDate")
  private XMLGregorianCalendar arrivalDate;

  @XmlElement(name = "Fares")
  private FaresXmlModel faresXmlModel;

}

