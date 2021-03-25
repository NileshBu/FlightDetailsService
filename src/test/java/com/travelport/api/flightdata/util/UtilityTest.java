package com.travelport.api.flightdata.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.travelport.api.flightdata.utils.Utility;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UtilityTest {


  @Test
  public void testGetDateString() throws DatatypeConfigurationException, ParseException {
    XMLGregorianCalendar xmlGregCal = getXmlGregorianCalendar("2021-03-24 11:15:00");
    assertEquals("24/03/2021", Utility.getDateString(xmlGregCal));

  }

  @Test
  public void testGetTimeString() throws DatatypeConfigurationException, ParseException {
    XMLGregorianCalendar xmlGregCal = getXmlGregorianCalendar("2021-03-24 11:15:00");
    assertEquals("11:15 a.m.", Utility.getTimeString(xmlGregCal));

  }

  private XMLGregorianCalendar getXmlGregorianCalendar(String dateString)
      throws ParseException, DatatypeConfigurationException {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    Date date = format.parse(dateString);
    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(date);
    return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
  }

}
