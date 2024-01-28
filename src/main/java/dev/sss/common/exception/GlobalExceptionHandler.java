package dev.sss.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        e.printStackTrace();
        log.error("Exception class : {}", e.getClass().getSimpleName());
        log.error("Exception message : {}", e.getMessage());

        return new ResponseEntity<>(new ErrorResponse(BAD_REQUEST, e.getMessage()), INTERNAL_SERVER_ERROR);
    }

}