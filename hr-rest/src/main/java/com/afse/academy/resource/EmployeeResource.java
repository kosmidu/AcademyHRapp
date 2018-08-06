package com.afse.academy.resource;

import com.afse.academy.Address;
import com.afse.academy.Department;
import com.afse.academy.Employee;
import com.afse.academy.RandomDateOfBirth;
import com.afse.academy.boundary.EmployeeBoundary;
import com.afse.academy.exception.InvalidInputException;
import com.afse.academy.exception.MyException;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    private Logger logger;

    @PostConstruct
    private void postConstruct() {
        logger.info("Initiated @PostConstruct method at EmployeeResource");
    }

    @EJB
    private EmployeeBoundary employeeBoundary;

    @POST
    @Path("/")
    public Response addEmployee(Employee e) throws InvalidInputException {
//        GenericResponse response = new GenericResponse();
//        if(emps.get(e.getId()) != null){
//            response.setStatus(false);
//            response.setMessage("Employee Already Exists");
//            response.setErrorCode("EC-01");
//            return Response.status(422).entity(response).build();
//        }
//        emps.put(e.getId(), e);
//        response.setStatus(true);
//        response.setMessage("Employee is created successfully");
        Employee response = employeeBoundary.create(e);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id : \\d+}") //digit one or more
    public Response deleteEmployee(@PathParam("id") Long id) throws InvalidInputException {
//        GenericResponse response = new GenericResponse();
//        if(emps.get(id) == null){
//            response.setStatus(false);
//            response.setMessage("Employee Does Not Exist");
//            response.setErrorCode("EC-02");
//            return Response.status(404).entity(response).build();
//        }
//        emps.remove(id);
//        response.setStatus(true);
//        response.setMessage("Employee is deleted successfully");
        String response = employeeBoundary.delete(id);
        return Response.ok(response).build();
    }

    @PUT
    @Path("/")
    public Response updateEmployee(Employee e) throws InvalidInputException {
        //String result = ejbService.updateEmployee(msg);
        //GenericResponse response = new GenericResponse();
        Employee result = employeeBoundary.update(e); //Boundary
//        if(result == null) {
//            response.setStatus(false);
//            response.setMessage("Employee Does Not Exist");
//            response.setErrorCode("EC-02");
//            return Response.status(404).entity(response).build();
//        }
//        response.setStatus(true);
//        response.setMessage("Employee is updated successfully");
//        response.setEmployee(result);
        return Response.ok(result).build();
    }

    @GET
    @Path("/{id : \\d+}")
    public Response getEmployee(@PathParam("id") Long id) throws InvalidInputException {
        Employee e = employeeBoundary.find(id);
        return Response.ok(e).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getDummy/{id : \\d+}")
    public Response getDummyEmployee(@PathParam("id") Long id) {
        Employee e = new Employee();
        e.setFirstName("Helen");
        e.setLastName("Doe");
        e.setBirthDate(RandomDateOfBirth.getRandomDOB());
        e.setAddress(new Address("Volos", "Greece", "Zachou", "61", "38333"));
        e.setPhoneNumber("6999999999");
        e.setEmail("user@example.com");
        e.setSalary(8976.55);
        e.setDepartment(new Department(30L, "afse", new Address("Athens", "Greece", "Alimos", "9", "17777")));
        e.setJoinDate(RandomDateOfBirth.getRandomDOB());
        e.setId(id);
        return Response.ok(e).build();
    }

    @GET
    @Path("/") // get all employees
    public Response getAllEmployees() {
        List<Employee> employees = employeeBoundary.getAll();
        return Response.ok(employees).build();
    }

    @GET
    @Path("/{id : \\d+}/departments") //get all employees by department id
    public Response getAllEmployeesByDepId(@PathParam("id") Long id) throws InvalidInputException {
        List<Employee> list = employeeBoundary.getAllByDepId(id);
        return Response.ok(list).build();
    }
}