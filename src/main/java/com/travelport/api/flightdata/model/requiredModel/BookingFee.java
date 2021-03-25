package com.travelport.api.flightdata.model.requiredModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingFee {

  private String currency;
  private Double amount;
}
