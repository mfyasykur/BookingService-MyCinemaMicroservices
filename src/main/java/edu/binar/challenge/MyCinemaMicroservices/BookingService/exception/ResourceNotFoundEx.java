package edu.binar.challenge.MyCinemaMicroservices.BookingService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundEx extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundEx(String message){
        super(message);
    }
}
