package com.travelport.api.flightdata.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.travelport.api.flightdata.service.FlightDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "XmlParserApi")
@Slf4j
public class FlightDetailsController {

  private FlightDetailsService xmlParserService;

  public FlightDetailsController(FlightDetailsService xmlParserService) {
    this.xmlParserService = xmlParserService;
  }


  @ApiOperation(value = "Get all flight as per user entry", notes = "Converts original url to short url",
      response = String.class)
  @RequestMapping(value = "/getFlights/{origin}/{destination}/{startDate}/{endDate}/{noOfPassengers}", method = RequestMethod.GET)
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<String> getFlights(@PathVariable(required = true) String origin,
      @PathVariable(required = true) String destination,
      @PathVariable(required = true) String startDate,
      @PathVariable(required = true) String endDate,
      @PathVariable(required = true) Integer noOfPassengers)
      throws JAXBException, JsonProcessingException {

    String availability = xmlParserService
        .getFlights(origin, destination, startDate, endDate, noOfPassengers);
    return ResponseEntity.ok(availability);

  }


}
