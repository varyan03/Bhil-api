package com.bfhl.api.controller;

import com.bfhl.api.dto.HealthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health check controller for monitoring API availability
 */
@RestController
public class HealthController {

    @Value("${app.official.email}")
    private String officialEmail;

    /**
     * Health check endpoint
     * 
     * @return HealthResponse with success status and official email
     */
    @GetMapping("/health")
    public ResponseEntity<HealthResponse> health() {
        HealthResponse response = new HealthResponse(true, officialEmail);
        return ResponseEntity.ok(response);
    }
}
