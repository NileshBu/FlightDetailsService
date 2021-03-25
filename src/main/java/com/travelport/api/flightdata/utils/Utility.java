package com.travelport.api.flightdata.utils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.xml.datatype.XMLGregorianCalendar;

public class Utility {


  public static String getDateString(XMLGregorianCalendar xgc) {

    Date date = xgc.toGregorianCalendar().getTime();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate = formatter.format(date);
    return formattedDate;
  }


  public static String getTimeString(XMLGregorianCalendar xgc) {

    Date date = xgc.toGregorianCalendar().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
    String formattedTime = sdf.format(date);
    return formattedTime;
  }

  public static String calculateTimeDifference(XMLGregorianCalendar departureDate,
      XMLGregorianCalendar arrivalDate) {

    LocalDateTime departure = departureDate.toGregorianCalendar().toZonedDateTime()
        .toLocalDateTime();
    LocalDateTime arrival = arrivalDate.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
    Duration dur = Duration.between(departure, arrival);
    long millis = dur.toMillis();
    String calculation = String.format("%02d:%02d",
        TimeUnit.MILLISECONDS.toHours(millis),
        TimeUnit.MILLISECONDS.toMinutes(millis) -
            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
        TimeUnit.MILLISECONDS.toSeconds(millis) -
            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    return calculation;

  }
}
