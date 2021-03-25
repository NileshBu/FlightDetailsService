package com.travelport.api.flightdata.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class XmlConversionException extends RuntimeException {

  private static final long serialVersionUID = 5634869299137716028L;

  public XmlConversionException(String exceptionMessage) {
    super(exceptionMessage);
    log.info(exceptionMessage);
  }

}
