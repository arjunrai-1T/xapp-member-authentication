package com.xapp.member.authentication.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SearchInputMetaValidator.class)
public @interface ValidSearchInputMeta {
    String message() default "Invalid SearchInputMeta";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}