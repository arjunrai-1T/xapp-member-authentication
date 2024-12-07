package com.xapp.member.authentication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import reactor.core.publisher.Mono;

public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorResponseException.class)
    public Mono<ResponseEntity<String>> handleCustomInternalServerError(ErrorResponseException ex) {
        // Create a response entity with HTTP 500 status and custom error message
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage()));
    }

//    // Optionally, handle other exceptions
//    @ExceptionHandler(Exception.class)
//    public Mono<ResponseEntity<String>> handleGeneralException(Exception ex) {
//        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage()));
//    }
}
