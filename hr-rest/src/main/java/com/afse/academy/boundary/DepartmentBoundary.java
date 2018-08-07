package com.afse.academy.boundary;

import com.afse.academy.entities.Department;
import com.afse.academy.GenericResponse;
import com.afse.academy.exception.InvalidInputException;
import com.afse.academy.service.DepartmentService;
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
public class DepartmentBoundary {
    @EJB
    private DepartmentService departmentService;

    @EJB
    private ValidationService validationService;

    @EJB
    private LocationValidationService locationValidationService;

    public GenericResponse create(Department d) throws ConstraintViolationException, InvalidInputException {
        GenericResponse response = new GenericResponse();

        // Hibernate Validation
        Set<ConstraintViolation<Department>> constraintViolations = validationService.validate(d);
        if(!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(constraintViolations));
        }

        // Custom Validation - Check combination [Country, City]
        checkCountryCityRelation(d, response);

        // Custom Validation - Check if department already exists
        if(d.getId() != null) {
            response.setResult(false);
            response.setMessage("Department ID already exists");
            response.setStatus(Response.Status.BAD_REQUEST);
            response.set(null);
            throw  new InvalidInputException(response.toString());
        }

        // Success create
        response.setResult(true);
        response.setMessage("Department is created successfully");
        response.setStatus(Response.Status.OK);
        response.set(departmentService.createDepartment(d));

        return response;
    }

    public GenericResponse delete(Long id) throws InvalidInputException {
        GenericResponse response = new GenericResponse();

        // Custom Validation - Check if department does not exist
        if (id == null || find(id).get() == null) {
            response.setResult(false);
            response.setMessage("Department does not exist");
            response.setStatus(Response.Status.BAD_REQUEST);
            response.set(null);
            throw new InvalidInputException(response.toString());
        }

        //Success delete
        response.setResult(true);
        response.setMessage("Department is deleted successfully");
        response.setStatus(Response.Status.OK);
        response.set(departmentService.deleteDepartment(id));

        return response;
    }

    public GenericResponse update(Department d) throws InvalidInputException {
        GenericResponse response = new GenericResponse();

        // Hibernate Validation
        Set<ConstraintViolation<Department>> constraintViolationSet = validationService.validate(d);
        if(!constraintViolationSet.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(constraintViolationSet));
        }

        // Custom Validation - Check combination [Country, City]
        checkCountryCityRelation(d, response);

        // Custom Validation - Check if department exists
        if(d.getId() == null || find(d.getId()).get() == null) {
            response.setResult(false);
            response.setMessage("Department does not exist");
            response.setStatus(Response.Status.CONFLICT);
            response.set(null);
            throw  new InvalidInputException(response.toString());
        }

        // Success update
        response.setResult(true);
        response.setMessage("Department is updated successfully");
        response.setStatus(Response.Status.OK);
        response.set(departmentService.updateDepartment(d));

        return response;
    }

    public GenericResponse find (Long id) throws InvalidInputException {
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
        response.set(departmentService.findDepartment(id));

        // Custom Validation - Check if department exists
        if(response.get() == null) {
            response.setResult(false);
            response.setMessage("Department is not found");
            response.setStatus(Response.Status.BAD_REQUEST);
            throw  new InvalidInputException(response.toString());
        }

        // Success retrieve for department
        response.setResult(true);
        response.setMessage("Department is found successfully");
        response.setStatus(Response.Status.OK);

        return response;
    }

    public List<Department> getAll() {
        return departmentService.getAllDepartments();
    }

    private void checkCountryCityRelation(Department d, GenericResponse response) throws InvalidInputException {
        if(!locationValidationService.validateLocation(d.getAddress().getCountry(), d.getAddress().getCity())) {
            response.setResult(false);
            response.setMessage("Combination Country - City does not exist");
            response.setStatus(Response.Status.BAD_REQUEST);
            response.set(null);
            throw new InvalidInputException(response.toString());
        }
    }
}
