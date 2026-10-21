package com.dev.resolveja.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse methodArgumentErrorResponse(MethodArgumentNotValidException e) {
        List<Error> errors = new ArrayList<>();

        e.getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            Error err = new Error(field, message);

            errors.add(err);
        });        

        return ErrorResponse.builder()
            .error("Erro nos campos json da requisição")
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .errorsList(errors)
        .build();
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> businessRuleException(ResponseStatusException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .error(e.getMessage())
            .timestamp(LocalDateTime.now())
            .status(e.getStatusCode().value())
            .errorsList(new ArrayList<>())
        .build();
        
        return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse globalException(Exception e) {
        return ErrorResponse.builder()
            .error(e.getMessage())
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .errorsList(new ArrayList<>())
        .build();
    }

}
