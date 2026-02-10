package com.bfhl.api.dto;

import com.bfhl.api.validation.SingleFieldRequired;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * Request DTO for POST /bfhl endpoint
 * Must contain exactly one of: fibonacci, prime, lcm, hcf, or AI
 */
@Data
@SingleFieldRequired
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BfhlRequest {

    private Integer fibonacci;

    private List<Integer> prime;

    private List<Integer> lcm;

    private List<Integer> hcf;

    private String AI;
}
