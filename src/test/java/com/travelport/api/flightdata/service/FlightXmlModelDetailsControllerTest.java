package com.travelport.api.flightdata.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.travelport.api.flightdata.controller.FlightDetailsController;
import com.travelport.api.flightdata.util.Helper;
import javax.xml.bind.JAXBException;
import net.minidev.json.parser.ParseException;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class FlightXmlModelDetailsControllerTest {

  @InjectMocks
  FlightDetailsController flightDetailsController;

  @Mock
  FlightDetailsService flightDetailsService;

  @DisplayName("Positive Test case for getFlights method")
  @Test
  public void testGetFlights()
      throws JAXBException, JsonProcessingException, ParseException, JSONException {

    Mockito.when(flightDetailsService.getFlights("DUB", "EDI",
        "24-03-2021", "25-03-2021", 4)).thenReturn(Helper.JSON);
    ResponseEntity<String> response = flightDetailsController.getFlights("DUB", "EDI",
        "24-03-2021", "25-03-2021", 4);
    assertEquals(
        response.getBody(), Helper.JSON);

  }


  @DisplayName("Negative Test case for getFlights method")
  @Test
  public void testGetFlightNeg()
      throws JAXBException, JsonProcessingException, ParseException, JSONException {

    Mockito.when(flightDetailsService.getFlights("DUB", "EDI",
        "24-03-2021", "25-03-2021", 4)).thenReturn(Helper.JSON);
    ResponseEntity<String> response = flightDetailsController.getFlights("DUB", "EDI",
        "24-03-2021", "25-03-2021", 4);
    assertEquals(
        response.getBody(), Helper.JSON);

  }
}
