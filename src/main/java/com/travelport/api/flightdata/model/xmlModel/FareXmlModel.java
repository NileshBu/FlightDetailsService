package com.travelport.api.flightdata.model.xmlModel;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "Fare")
@XmlAccessorType(XmlAccessType.FIELD)
public class FareXmlModel {

  @XmlAttribute(name = "class")
  private String fareClass;

  @XmlElement(name = "BasePrice")
  private String basePrice;

  @XmlElement(name = "Fees")
  private String fees;

  @XmlElement(name = "Tax")
  private String tax;
}
