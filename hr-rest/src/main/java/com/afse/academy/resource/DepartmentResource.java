package com.afse.academy.resource;
import com.afse.academy.entities.Address;
import com.afse.academy.entities.Department;
import com.afse.academy.GenericResponse;
import com.afse.academy.boundary.DepartmentBoundary;
import com.afse.academy.exception.InvalidInputException;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/departments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentResource {
    @Inject
    private Logger logger;

    @PostConstruct
    private void postConstruct() {
        logger.info("Initiated @PostConstruct method at DepartmentResource");
    }

    @EJB
    private DepartmentBoundary departmentBoundary;

    @POST
    @Path("/")
    public Response addDepartment(Department d) throws InvalidInputException {
        GenericResponse response = departmentBoundary.create(d);
        return Response.ok(response.toString()).build();
    }

    @DELETE
    @Path("/{id : \\d+}") //reggex: digit one or more
    public Response deleteDepartment(@PathParam("id") Long id) throws InvalidInputException {
        GenericResponse response = departmentBoundary.delete(id);
        return Response.ok(response.toString()).build();
    }

    @PUT
    @Path("/")
    public Response updateDepartment(Department d) throws InvalidInputException {
        GenericResponse result = departmentBoundary.update(d);
        return Response.ok(result.toString()).build();
    }

    @GET
    @Path("/{id : \\d+}") //reggex: digit one or more
    public Response getDepartment(@PathParam("id") Long id) throws InvalidInputException {
        GenericResponse genericResponse =  departmentBoundary.find(id);

        if(genericResponse.isResult()) {
            Department response = (Department) genericResponse.get();
            return Response.ok(response).build();
        }
        return Response.ok(genericResponse).build();
    }

    @GET
    @Path("/") // get all Departments
    public Response getAllDepartments() {
        List<Department> departments = departmentBoundary.getAll();
        return Response.ok(departments).build();
    }

    @GET
    @Path("/getDummy/{id : \\d+}") //Dummy info for department
    public Response getDummyDepartment(@PathParam("id") Long id) {
        Department d = new Department();
        d.setId(id);
        d.setName("Software Engineer");
        d.setAddress(new Address("Volos", "Greece", "Zachou", "61", "38333"));
        return Response.ok(d).build();
    }
}