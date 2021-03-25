package com.travelport.api.flightdata.model.requiredModel;

import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Availability {

  private List<Flight> flight;

}
