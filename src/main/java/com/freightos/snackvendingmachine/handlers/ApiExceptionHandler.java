package com.freightos.snackvendingmachine.handlers;

import com.freightos.snackvendingmachine.exceptions.InvalidInputException;
import com.freightos.snackvendingmachine.exceptions.ItemUnavailableException;
import com.freightos.snackvendingmachine.exceptions.NoEnoughChangeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {InvalidInputException.class})
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException exception) {
        APIException apiException = new APIException(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ItemUnavailableException.class})
    public ResponseEntity<Object> handleItemUnavailableException(ItemUnavailableException exception) {
        APIException apiException = new APIException(
                exception.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NoEnoughChangeException.class})
    public ResponseEntity<Object> handleNoEnoughChangeException(NoEnoughChangeException exception) {
        APIException apiException = new APIException(
                exception.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
}
