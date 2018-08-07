package com.afse.academy.boundary;

import com.afse.academy.entities.Employee;
import com.afse.academy.GenericResponse;
import com.afse.academy.exception.InvalidInputException;
import com.afse.academy.service.EmployeeService;
import com.afse.academy.validation.LocationValidationService;
import com.afse.academy.validation.ValidationService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
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

    public GenericResponse create(Employee e) throws ConstraintViolationException, InvalidInputException {
        GenericResponse response = new GenericResponse();

        // Hibernate Validation
        Set<ConstraintViolation<Employee>> constraintViolationSet = validationService.validate(e);
        if(!constraintViolationSet.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(constraintViolationSet));
        }

        // Custom Validation - Check combination [Country, City]
        checkCountryCityRelation(e, response);

        // Custom Validation - Check if employee already exists
        if(e.getId() != null) {
            response.setResult(false);
            response.setMessage("Employee ID already exists");
            response.setStatus(Response.Status.BAD_REQUEST);
            response.set(null);
            throw  new InvalidInputException(response.toString());
        }

        // Success create
        response.setResult(true);
        response.setMessage("Employee is created successfully");
        response.setStatus(Response.Status.OK);
        response.set(employeeService.createEmployee(e));

        return response;
    }

    public GenericResponse delete(Long id) throws InvalidInputException {
        GenericResponse response = new GenericResponse();

        // Custom Validation - Check if employee does not exist
        if (id == null || find(id).get() == null) {
            response.setResult(false);
            response.setMessage("Employee does not exist");
            response.setStatus(Response.Status.BAD_REQUEST);
            response.set(null);
            throw new InvalidInputException(response.toString());
        }

        //Success delete
        response.setResult(true);
        response.setMessage("Employee is deleted successfully");
        response.setStatus(Response.Status.OK);
        response.set(employeeService.deleteEmployee(id));

        return response;
    }

    public GenericResponse update(Employee e) throws InvalidInputException {
        GenericResponse response = new GenericResponse();

        // Hibernate Validation
        Set<ConstraintViolation<Employee>> constraintViolationSet = validationService.validate(e);
        if(!constraintViolationSet.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(constraintViolationSet));
        }

        // Custom Validation - Check combination [Country, City]
        checkCountryCityRelation(e, response);

        // Custom Validation - Check if employee exists
        if(e.getId() == null || find(e.getId()).get() == null) {
            response.setResult(false);
            response.setMessage("Employee does not exist");
            response.setStatus(Response.Status.CONFLICT);
            response.set(null);
            throw  new InvalidInputException(response.toString());
        }

        // Success update
        response.setResult(true);
        response.setMessage("Employee is updated successfully");
        response.setStatus(Response.Status.OK);
        response.set(employeeService.updateEmployee(e));

        return response;
    }

    public GenericResponse find (Long id) throws InvalidInputException {
        GenericResponse response = new GenericResponse();

        // Custom Validation - Check if employee id is null
        if (id == null) {
            response.setResult(false);
            response.setMessage("Employee ID is null");
            response.setStatus(Response.Status.BAD_REQUEST);
            response.set(null);
            throw  new InvalidInputException(response.toString());
        }

        // Success search for employee
        response.set(employeeService.findEmployee(id));

        // Custom Validation - Check if employee exists
        if(response.get() == null) {
            response.setResult(false);
            response.setMessage("Employee is not found");
            response.setStatus(Response.Status.BAD_REQUEST);
            throw  new InvalidInputException(response.toString());
        }

        // Success retrieve for employee
        response.setResult(true);
        response.setMessage("Employee is found successfully");
        response.setStatus(Response.Status.OK);

        return response;
    }

    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }

    public GenericResponse getAllByDepId(Long id) throws InvalidInputException {
        GenericResponse response = new GenericResponse();

        // Custom Validation - Check if department id is null
        if (id == null) {
            response.setResult(false);
            response.setMessage("Department ID is null");
            response.setStatus(Response.Status.BAD_REQUEST);
            response.set(null);
            throw  new InvalidInputException(response.toString());
        }

        // Success search for department
        response.setList(employeeService.getAllEmployeesByDepId(id));

        // Custom Validation - Check if department exists
        if(response.getList().isEmpty()){
            response.setResult(false);
            response.setMessage("Department is not found");
            response.setStatus(Response.Status.BAD_REQUEST);
            throw  new InvalidInputException(response.toString());
        }

        // Success retrieve list of employees by dep id
        response.setResult(true);
        response.setMessage("Employees by depID are found successfully");
        response.setStatus(Response.Status.OK);

        return response;
    }

    private void checkCountryCityRelation(Employee e, GenericResponse response) throws InvalidInputException {
        if(!locationValidationService.validateLocation(e.getAddress().getCountry(), e.getAddress().getCity())) {
            response.setResult(false);
            response.setMessage("Combination Country - City does not exist");
            response.setStatus(Response.Status.BAD_REQUEST);
            response.set(null);
            throw new InvalidInputException(response.toString());
        }
    }
}