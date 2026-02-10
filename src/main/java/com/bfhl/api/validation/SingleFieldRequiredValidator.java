package com.bfhl.api.validation;

import com.bfhl.api.dto.BfhlRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator implementation for SingleFieldRequired annotation
 * Ensures exactly one functional key is present in the request
 */
public class SingleFieldRequiredValidator implements ConstraintValidator<SingleFieldRequired, BfhlRequest> {

    @Override
    public boolean isValid(BfhlRequest request, ConstraintValidatorContext context) {
        if (request == null) {
            return false;
        }

        int nonNullCount = 0;

        if (request.getFibonacci() != null)
            nonNullCount++;
        if (request.getPrime() != null)
            nonNullCount++;
        if (request.getLcm() != null)
            nonNullCount++;
        if (request.getHcf() != null)
            nonNullCount++;
        if (request.getAI() != null)
            nonNullCount++;

        return nonNullCount == 1;
    }
}
