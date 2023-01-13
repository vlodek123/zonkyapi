package ct.vt.zonkyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ZonkyErrorResponseException extends RuntimeException{

    public ZonkyErrorResponseException(String message) {
        super(message);
    }
}
