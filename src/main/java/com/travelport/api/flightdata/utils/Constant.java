package com.travelport.api.flightdata.utils;

public class Constant {

  public static final String URI =
      "http://private-anon-36f987f0b6-mockairline.apiary-mock.com/flights/" +
          "{origin}/{destination}/{startDate}/{endDate}/{noOfPassengers}";


  public static final String METHOD_CALL_WITH_PARAMETERS = "Calling method {} with input parameters: {}";
  public static final String METHOD_CALL_WITHOUT_PARAMETERS = "Calling method {} without input parameters";
  public static final String METHOD_END_WITH_OUTPUT_PARAMETERS = "Ending method, {} with output parameters: {}";
  public static final String METHOD_END_WITHOUT_PARAMETERS = "Ending method, {} without output parameters";

  public static final String ERROR = "error occurred";
  public static final String RUNTIME_EXCEPTION_MESSAGE = "500 Status Code";

  public static final String ORIGIN = "origin";
  public static final String DESTINATION = "destination";
  public static final String START_DATE = "startDate";
  public static final String END_DATE = "endDate";
  public static final String NO_OF_PASSENGERS = "noOfPassengers";
  public static final String EMPTY = "";


}
