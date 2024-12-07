package com.xapp.member.authentication.validator;

import com.xapp.member.authentication.models.common.SearchInputMeta;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SearchInputMetaValidator implements ConstraintValidator<ValidSearchInputMeta, SearchInputMeta> {

    @Override
    public void initialize(ValidSearchInputMeta constraintAnnotation) {
        // Initialization if needed
    }

    @Override
    public boolean isValid(SearchInputMeta value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;  // You can customize the logic here
        }
        // Add custom validation logic
        return value.getCorrelationId() != null && !value.getCorrelationId().isBlank();
    }
}