package org.katrin.feedbackme.advicer;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<String> handleEntityNotFoundException() {
        return new ResponseEntity<>(ExceptionMessages.NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IllegalArgumentException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<String> handleIllegalArgumentException() {
        return new ResponseEntity<>(ExceptionMessages.INVALID_INPUT.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
