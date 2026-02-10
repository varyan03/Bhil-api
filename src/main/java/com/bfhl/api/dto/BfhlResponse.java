package com.bfhl.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Standard response for POST /bfhl endpoint
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BfhlResponse {

    @JsonProperty("is_success")
    private boolean isSuccess;

    @JsonProperty("official_email")
    private String officialEmail;

    private Object data;

    private String error;

    public BfhlResponse(boolean isSuccess, String officialEmail, Object data) {
        this.isSuccess = isSuccess;
        this.officialEmail = officialEmail;
        this.data = data;
    }
}
