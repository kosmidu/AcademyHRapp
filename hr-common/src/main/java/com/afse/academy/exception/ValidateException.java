package com.afse.academy.exception;

import javax.validation.ConstraintViolation;
import java.io.Serializable;
import java.util.Set;

public class ValidateException extends Exception {

    private Set<ConstraintViolation<Serializable>> constraintViolations;

    public ValidateException(Set<ConstraintViolation<Serializable>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }

    public Set<ConstraintViolation<Serializable>> getConstraintViolations() {
        return constraintViolations;
    }
}