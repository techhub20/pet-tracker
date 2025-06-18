package com.tractive.pet_tracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/* Global Exception Handler Class */

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(InvalidTrackerTypeException.class)
    public ResponseEntity<Errorhandler> handleError(InvalidTrackerTypeException ex) {
        Errorhandler error = new Errorhandler(false, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Errorhandler> handleIllegalError(IllegalArgumentException ex) {
        Errorhandler error = new Errorhandler(false, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

}
