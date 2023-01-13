package ct.vt.zonkyapi;

import ct.vt.zonkyapi.exception.ErrorResponse;
import ct.vt.zonkyapi.exception.ZonkyErrorResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ZonkyapiApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ZonkyErrorResponseException.class)
    public ResponseEntity<Object> handleZonkyErrorResponseException(ZonkyErrorResponseException ex) {
        List<String> listErrors = Arrays.asList(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(listErrors);
        System.out.println(error.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
