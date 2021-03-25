package com.travelport.api.flightdata.model.requiredModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarePrices {

    private First first;
    private Business business;
    private Economy economy;


}
