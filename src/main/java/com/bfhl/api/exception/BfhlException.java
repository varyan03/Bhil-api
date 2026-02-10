package com.bfhl.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Custom exception for BFHL API business logic errors
 */
@Getter
public class BfhlException extends RuntimeException {

    private final HttpStatus status;

    public BfhlException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public BfhlException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
}
