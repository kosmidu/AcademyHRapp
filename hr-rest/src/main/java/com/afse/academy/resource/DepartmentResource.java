package com.afse.academy.resource;
import com.afse.academy.Address;
import com.afse.academy.Department;
import com.afse.academy.exception.MyException;
import com.afse.academy.boundary.DepartmentBoundary;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

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
    public Response addDepartment(Department d) throws MyException {
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
        Department response = departmentBoundary.create(d);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDepartment(@PathParam("id") Long id) throws MyException {
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
        String response = departmentBoundary.delete(id);
        return Response.ok(response).build();
    }

    @PUT
    @Path("/")
    public Response updateDepartment(Department d) throws MyException {
        //String result = ejbService.updateEmployee(msg);
        //GenericResponse response = new GenericResponse();
        Department result = departmentBoundary.update(d); //Boundary
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
    @Path("/{id}")
    public Response getDepartment(@PathParam("id") Long id) throws MyException {
        Department d =  departmentBoundary.find(id);
        return Response.ok(d).build();
    }

    @GET
    @Path("/getDummy/{id}")
    public Response getDummyDepartment(@PathParam("id") Long id) {
        Department d = new Department();
        d.setId(id);
        d.setName("AFSE");
        d.setAddress(new Address("Volos", "Greece", "Zachou", "61", "38333"));
        return Response.ok(d).build();
    }

    @GET
    @Path("/")// get all Departments
    public Response getAllDepartments() {
        List<Department> list = departmentBoundary.getAll();
        return Response.ok(list).build();
    }
}