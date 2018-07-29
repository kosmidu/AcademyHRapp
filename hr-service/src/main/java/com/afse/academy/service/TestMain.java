package com.afse.academy.service;

import com.afse.academy.Employee;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class TestMain {
    public static void main(String[] args) {
        Employee emp = new Employee(8,null,"Kos","SW", "kosmi@uth.gr");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Employee>> vals = validator.validate(emp);
        for (ConstraintViolation<Employee> c:vals) {
            System.out.println(c.getMessage());
        }
    }
}
