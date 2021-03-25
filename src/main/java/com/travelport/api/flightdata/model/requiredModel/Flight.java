package com.travelport.api.flightdata.model.requiredModel;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Flight {

    private String operator;
    private String flightNumber;
    private String departsFrom;
    private String arrivesAt;
    private DepartsOn departsOn;
    private ArrivesOn arrivesOn;
    private String flightTime;
    private FarePrices farePrices;




}

