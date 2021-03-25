package com.travelport.api.flightdata;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.travelport.api.flightdata.controller.FlightDetailsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XmlparserApplicationTests {

  @Autowired
  FlightDetailsController flightDetailsController;

  @Test
  void contextLoads() {
    assertNotNull(flightDetailsController);
  }

}
