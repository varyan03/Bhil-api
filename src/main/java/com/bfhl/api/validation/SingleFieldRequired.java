package com.bfhl.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation annotation to ensure exactly one functional key is present
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SingleFieldRequiredValidator.class)
public @interface SingleFieldRequired {
    String message() default "Exactly one functional key (fibonacci, prime, lcm, hcf, or AI) must be present";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
