package com.travelport.api.flightdata.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonParser;
import com.travelport.api.flightdata.exception.XmlConversionException;
import com.travelport.api.flightdata.util.Helper;
import com.travelport.api.flightdata.utils.Constant;
import java.util.Map;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class FlightXmlModelDetailsServiceTest {

  @InjectMocks
  FlightDetailsService flightDetailService;

  @Mock
  RestTemplate restTemplate;

  @DisplayName("Positive Test case for getFlights method")
  @Test
  public void testGetFlights() throws JAXBException, JsonProcessingException {
    Map<String, Object> parameters = Helper.getStringObjectMap();

    HttpHeaders httpHeaders = new HttpHeaders();
    HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
    JsonParser parser = new JsonParser();

    Mockito.when(restTemplate.exchange(Constant.URI, HttpMethod.GET, httpEntity, String.class,
        parameters)).thenReturn(new ResponseEntity(Helper.XML, HttpStatus.OK));
    String result = flightDetailService.getFlights("DUB", "EDI",
        "24-03-2021", "25-03-2021", 4);
    assertNotNull(result);
    assertEquals(parser.parse(result), parser.parse(Helper.JSON));

  }

  @DisplayName("Negative Test case for getFlights method")
  @Test
  public void testGetFlightsNeg() throws JAXBException {

    Map<String, Object> parameters = Helper.getStringObjectMap();

    HttpHeaders httpHeaders = new HttpHeaders();
    HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

    Mockito.when(restTemplate.exchange(Constant.URI, HttpMethod.GET, httpEntity, String.class,
        parameters)).thenReturn(new ResponseEntity(Helper.XML_INVALID, HttpStatus.OK));

    Assertions.assertThrows(XmlConversionException.class, () -> {
      flightDetailService.getFlights(
          "DUB", "EDI", "24-03-2021", "25-03-2021", 4);
    });
  }


}
