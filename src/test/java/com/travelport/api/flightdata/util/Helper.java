package com.travelport.api.flightdata.util;

import com.travelport.api.flightdata.utils.Constant;

import java.util.HashMap;
import java.util.Map;

public class Helper {
    
    public static final String XML ="<Availability>" +
            "  <Flight>" +
            "    <CarrierCode>EI</CarrierCode>" +
            "    <FlightDesignator>EI554</FlightDesignator>" +
            "    <OriginAirport>IST</OriginAirport>" +
            "    <DestinationAirport>DUB</DestinationAirport>" +
            "    <DepartureDate>2014-01-02T10:48:00.000Z</DepartureDate>" +
            "    <ArrivalDate>2014-01-02T13:04:00.000Z</ArrivalDate>" +
            "   <Fares>" +
            " <Fare class='FIF'> " +
            "<BasePrice>EUR 272.00</BasePrice>" +
            "<Fees>EUR 17.00</Fees>" +
            "<Tax>EUR 13.60</Tax>" +
            "</Fare>" +
            "<Fare class='CIF'>" +
            "<BasePrice>EUR 136.00</BasePrice>" +
            "<Fees>EUR 17.00</Fees>" +
            "<Tax>EUR 13.60</Tax>" +
            "</Fare>" +
            "<Fare class='YIF'>" +
            "<BasePrice>EUR 68.00</BasePrice>" +
            "<Fees>EUR 17.00</Fees>" +
            "<Tax>EUR 13.60</Tax>" +
            "</Fare>" +
            "</Fares>" +
            "</Flight>" +
            "</Availability>";

    public static final String XML_INVALID ="<Availability>" +
            "  <Flight>" +
            "    <CarrierCode>EI</CarrierCode>" +
            "    <FlightDesignator>EI554</FlightDesignator>" +
            "    <OriginAirport>IST</OriginAirport>";
    
    public static final String JSON ="{" +
            "  \"availability\" : [ {" +
            "    \"flight\" : [ {" +
            "      \"operator\" : \"EI\"," +
            "      \"flightNumber\" : \"EI554\"," +
            "      \"departsFrom\" : \"IST\"," +
            "      \"arrivesAt\" : \"DUB\"," +
            "      \"departsOn\" : {" +
            "        \"date\" : \"02/01/2014\"," +
            "        \"time\" : \"10:48 a.m.\"" +
            "      }," +
            "      \"arrivesOn\" : {" +
            "        \"date\" : \"02/01/2014\"," +
            "        \"time\" : \"01:04 p.m.\"" +
            "      }," +
            "      \"flightTime\" : \"02:16\"," +
            "      \"farePrices\" : {" +
            "        \"first\" : {" +
            "          \"ticket\" : {" +
            "            \"currency\" : \"EU\"," +
            "            \"amount\" : 272.0" +
            "          }," +
            "          \"bookingFee\" : {" +
            "            \"currency\" : \"EU\"," +
            "            \"amount\" : 17.0" +
            "          }," +
            "          \"tax\" : {" +
            "            \"currency\" : \"EU\"," +
            "            \"amount\" : 13.6" +
            "          }" +
            "        }," +
            "        \"business\" : {" +
            "          \"ticket\" : {" +
            "            \"currency\" : \"EU\"," +
            "            \"amount\" : 136.0" +
            "          }," +
            "          \"bookingFee\" : {" +
            "            \"currency\" : \"EU\"," +
            "            \"amount\" : 17.0" +
            "          }," +
            "          \"tax\" : {" +
            "            \"currency\" : \"EU\"," +
            "            \"amount\" : 13.6" +
            "          }" +
            "        }," +
            "        \"economy\" : {" +
            "          \"ticket\" : {" +
            "            \"currency\" : \"EU\"," +
            "            \"amount\" : 68.0" +
            "          }," +
            "          \"bookingFee\" : {" +
            "            \"currency\" : \"EU\"," +
            "            \"amount\" : 17.0" +
            "          }," +
            "          \"tax\" : {" +
            "            \"currency\" : \"EU\"," +
            "            \"amount\" : 13.6" +
            "          }" +
            "        }" +
            "      }" +
            "    }" +
            "  ]" +
            "  } ]" +
            "}";

    public static Map<String, Object> getStringObjectMap() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(Constant.ORIGIN, "DUB");
        parameters.put(Constant.DESTINATION, "EDI");
        parameters.put(Constant.START_DATE, "24-03-2021");
        parameters.put(Constant.END_DATE, "25-03-2021");
        parameters.put(Constant.NO_OF_PASSENGERS, 4);
        return parameters;
    }
}
