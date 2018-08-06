package com.afse.academy.boundary;

import com.afse.academy.Employee;
import com.afse.academy.exception.InvalidInputException;
import com.afse.academy.service.EmployeeService;
import com.afse.academy.validation.LocationValidationService;
import com.afse.academy.validation.ValidationService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class EmployeeBoundary {
    @EJB
    private EmployeeService employeeService;

    @EJB
    private ValidationService validationService;

    @EJB
    private LocationValidationService locationValidationService;

    public Employee create(Employee e) throws ConstraintViolationException, InvalidInputException {
        Set<ConstraintViolation<Employee>> constraintViolationSet = validationService.validateEmployee(e);
        if(!constraintViolationSet.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(constraintViolationSet));
        }
        if(locationValidationService.validateLocation(e.getAddress().getCountry(), e.getAddress().getCity())) {
            return employeeService.createEmployee(e);
        } else {
            throw new InvalidInputException();
        }
        //return employeeService.createEmployee(e);
    }

    public String delete(Long id) throws InvalidInputException {
        if(id != null && find(id) != null) { //if employee exists, delete
            return employeeService.deleteEmployee(id);
        } else {
            throw new InvalidInputException();
        }
    }

    public Employee update(Employee e) throws InvalidInputException {
        if (e.getId() != null && find(e.getId()) != null) { //if employee exists, update
            validationService.validateEmployee(e);
            return employeeService.updateEmployee(e);
        } else {
            throw new InvalidInputException();
        }
    }

    public Employee find (Long id) throws InvalidInputException {
        if(id != null) {
            return employeeService.findEmployee(id);
        } else {
            throw new InvalidInputException();
        }
    }

    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }

    public List<Employee> getAllByDepId(Long id) throws InvalidInputException {
        if (id != null) {
            return employeeService.getAllEmployeesByDepId(id);
        } else {
            throw new InvalidInputException();
        }
    }
}