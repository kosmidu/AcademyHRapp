package com.afse.academy.validation;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.Set;

/**
 *  This is a generic validation service for Hibernate annotations
 * @param <T> Serializable
 */
@Stateless
public class ValidationService <T extends Serializable> {
    @Inject
    private Validator validator;

    public Set<ConstraintViolation<T>> validate(T t) {
        return validator.validate(t);
    }

    //TODO return var for debug
}