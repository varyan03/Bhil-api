package com.bfhl.api.exception;

import com.bfhl.api.dto.BfhlResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler for the BFHL API
 * Ensures all errors return proper structure with is_success = false
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${app.official.email}")
    private String officialEmail;

    /**
     * Handle validation errors (400 Bad Request)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BfhlResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    if (error instanceof FieldError) {
                        return ((FieldError) error).getField() + ": " + error.getDefaultMessage();
                    }
                    return error.getDefaultMessage();
                })
                .collect(Collectors.joining(", "));

        BfhlResponse response = new BfhlResponse();
        response.setSuccess(false);
        response.setOfficialEmail(officialEmail);
        response.setError(errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handle malformed JSON or type mismatches (400 Bad Request)
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BfhlResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        BfhlResponse response = new BfhlResponse();
        response.setSuccess(false);
        response.setOfficialEmail(officialEmail);
        response.setError("Invalid request format or data type mismatch");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handle illegal argument exceptions (400 Bad Request)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BfhlResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        BfhlResponse response = new BfhlResponse();
        response.setSuccess(false);
        response.setOfficialEmail(officialEmail);
        response.setError(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Handle custom BFHL exceptions
     */
    @ExceptionHandler(BfhlException.class)
    public ResponseEntity<BfhlResponse> handleBfhlException(BfhlException ex) {
        BfhlResponse response = new BfhlResponse();
        response.setSuccess(false);
        response.setOfficialEmail(officialEmail);
        response.setError(ex.getMessage());

        return ResponseEntity.status(ex.getStatus()).body(response);
    }

    /**
     * Handle all other unexpected exceptions (500 Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BfhlResponse> handleGenericException(Exception ex) {
        BfhlResponse response = new BfhlResponse();
        response.setSuccess(false);
        response.setOfficialEmail(officialEmail);
        response.setError("An unexpected error occurred. Please try again later.");

        // Log the actual error for debugging (not exposed to user for security)
        ex.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
