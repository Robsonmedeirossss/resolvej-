package com.dev.resolveja.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder 
public class ErrorResponse {
    private String error;
    private LocalDateTime timestamp;
    private int status;
    private List<Error> errorsList;
}
