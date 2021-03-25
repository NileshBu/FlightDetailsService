package com.travelport.api.flightdata.exception;

import com.travelport.api.flightdata.utils.Constant;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Fall-back handler – a catch-all type of logic that deals with all other exceptions that don't
   * have specific handlers and the internal exceptions such as ResourceNotFoundException
   */
  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
    logger.error(ex.getMessage());

    if (ex instanceof DataRetrievalException) {
      final ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND,
          ex.getLocalizedMessage(), Constant.ERROR);
      return new ResponseEntity<>(apiErrorResponse, new HttpHeaders(),
          apiErrorResponse.getStatus());
    }
    final ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
        ex.getLocalizedMessage(), Constant.ERROR);

    return new ResponseEntity<>(apiErrorResponse, new HttpHeaders(), apiErrorResponse.getStatus());
  }

  /**
   * Fall-back handler – a catch-all the runtime exceptions
   */
  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<Object> handleAllRuntimeException(final RuntimeException ex,
      final WebRequest request) {
    logger.error(Constant.RUNTIME_EXCEPTION_MESSAGE, ex);

    final ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
        Constant.RUNTIME_EXCEPTION_MESSAGE, Constant.ERROR);

    return new ResponseEntity<>(apiErrorResponse, new HttpHeaders(), apiErrorResponse.getStatus());
  }


}
