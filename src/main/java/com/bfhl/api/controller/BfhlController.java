package com.bfhl.api.controller;

import com.bfhl.api.dto.BfhlRequest;
import com.bfhl.api.dto.BfhlResponse;
import com.bfhl.api.service.MathService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main controller for BFHL API endpoints
 */
@RestController
public class BfhlController {

    @Value("${app.official.email}")
    private String officialEmail;

    @Autowired
    private MathService mathService;

    /**
     * POST /bfhl endpoint handling all functional keys
     * 
     * @param request the request containing exactly one functional key
     * @return response with appropriate data
     */
    @PostMapping("/bfhl")
    public ResponseEntity<BfhlResponse> processBfhl(@Valid @RequestBody BfhlRequest request) {
        Object data = null;

        // Process based on which field is present
        if (request.getFibonacci() != null) {
            data = mathService.generateFibonacci(request.getFibonacci());
        } else if (request.getPrime() != null) {
            data = mathService.filterPrimes(request.getPrime());
        } else if (request.getLcm() != null) {
            data = mathService.calculateLCM(request.getLcm());
        } else if (request.getHcf() != null) {
            data = mathService.calculateHCF(request.getHcf());
        } else if (request.getAI() != null) {
            // TODO: Implement AI service integration
            data = "NotImplementedYet";
        }

        BfhlResponse response = new BfhlResponse(true, officialEmail, data);
        return ResponseEntity.ok(response);
    }
}
