package com.afse.academy.validation;

import com.afse.academy.Department;
import com.afse.academy.Employee;
import com.afse.academy.Location;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Stateless
public class ValidationService {
    @Inject
    private Validator validator;

    //TODO make it generic
    public Set<ConstraintViolation<Employee>> validateEmployee(Employee employee) {
        return validator.validate(employee);
    }

    public Set<ConstraintViolation<Department>> validateDepartment(Department department) {
        return validator.validate(department);
    }

    public Set<ConstraintViolation<Location>> validateLocation(Location location) {
        return validator.validate(location); //TODO return var for debug
    }
}