package com.afse.academy.resource;

import com.afse.academy.*;
import com.afse.academy.boundary.EmployeeBoundary;
import com.afse.academy.entities.Address;
import com.afse.academy.entities.Department;
import com.afse.academy.entities.Employee;
import com.afse.academy.exception.InvalidInputException;
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
        GenericResponse response = employeeBoundary.create(e);
        return Response.ok(response.toString()).build();
    }

    @DELETE
    @Path("/{id : \\d+}") //reggex: digit one or more
    public Response deleteEmployee(@PathParam("id") Long id) throws InvalidInputException {
        GenericResponse response = employeeBoundary.delete(id);
        return Response.ok(response.toString()).build();
    }

    @PUT
    @Path("/")
    public Response updateEmployee(Employee e) throws InvalidInputException {
        GenericResponse response = employeeBoundary.update(e);
        return Response.ok(response.toString()).build();
    }

    @GET
    @Path("/{id : \\d+}") //reggex: digit one or more
    public Response getEmployee(@PathParam("id") Long id) throws InvalidInputException {
        GenericResponse genericResponse = employeeBoundary.find(id);

        if(genericResponse.isResult()) {
            Employee response = (Employee) genericResponse.get();
            return Response.ok(response).build();
        }
        return Response.ok(genericResponse).build();
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
        GenericResponse genericResponse = employeeBoundary.getAllByDepId(id);

        if(genericResponse.isResult()) {
            List<Employee> response = genericResponse.getList();
            return Response.ok(response).build();
        }
        return Response.ok(genericResponse).build();
    }

    @GET
    @Path("/getDummy/{id : \\d+}") // Dummy info for employee
    public Response getDummyEmployee(@PathParam("id") Long id) {
        Employee e = new Employee();
        e.setFirstName("Helen");
        e.setLastName("Doe");
        e.setBirthDate(RandomDateOfBirth.getRandomDOB());
        e.setAddress(new Address("Volos", "Greece", "Zachou", "61", "38333"));
        e.setPhoneNumber("6999999999");
        e.setEmail("user@example.com");
        e.setSalary(8976.55);
        e.setDepartment(new Department(30L, "System Architect", new Address("Athens", "Greece", "Alimos", "9", "17777")));
        e.setJoinDate(RandomDateOfBirth.getRandomDOB());
        e.setId(id);
        return Response.ok(e).build();
    }
}