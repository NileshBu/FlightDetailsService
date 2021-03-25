package com.travelport.api.flightdata.model.xmlModel;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "Fares")
@XmlAccessorType(XmlAccessType.FIELD)
public class FaresXmlModel {

  @XmlElement(name = "Fare")
  private List<FareXmlModel> fareXmlModel;


}

