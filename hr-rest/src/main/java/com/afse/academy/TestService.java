package com.afse.academy;

import com.afse.academy.service.EjbService;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/crud")
public class TestService {

    @Inject
    private Logger logger;

    @PostConstruct
    private void postConstruct() {
        logger.info("Initiated @PostConstruct method");
    }

    @EJB
    private EjbService ejbService;
//    @EJB
//    private com.afse.academy.DaoImpl dao;

//    @GET
//    @Path("/maria")
//    public Response printMaria() {
//        String result = ejbService.getEjbService();
//        return Response.status(200).entity(result).build();
//    }
//
//    @POST
//    @Path("/create")
//    public Response printMessage() {
//        String result = dao.test();
//        return Response.status(200).entity(result).build();
//    }
//
//    @GET
//    @Path("/find/{param}")
//    public Response printMes(@PathParam("param") Integer msg) {
//        String result = dao.testFind(msg);
//        return  Response.status(200).entity(result).build();
//    }

    @PUT
    @Path("/update/{param}")
    public Response printMesUp(@PathParam("param") Integer msg) throws MyException {
        String result = ejbService.getEjbService(msg);
        //String result = dao.testUpdate(msg);
        return Response.ok(result).build();
    }

//    @DELETE
//    @Path("/delete/{id}")
//    public Response printMesDel(@PathParam("id") Integer msg){
//        String res = dao.testDelete(msg);
//        return Response.status(200).entity(res).build();
//    }

}