package com.travelport.api.flightdata.model.requiredModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Economy {

    private Ticket ticket;
    private BookingFee bookingFee;
    private Tax tax;
}
