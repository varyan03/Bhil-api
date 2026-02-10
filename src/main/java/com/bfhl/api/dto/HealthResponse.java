package com.bfhl.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Standard response for health check endpoint
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthResponse {
    private boolean is_success;
    private String official_email;
}
