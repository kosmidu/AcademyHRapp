package com.afse.academy.resource;
import com.afse.academy.Location;
import com.afse.academy.boundary.LocationBoundary;
import com.afse.academy.service.LocationService;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/locations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LocationResource {
    @Inject
    private Logger logger;

    @PostConstruct
    private void postConstruct() {
        logger.info("Initiated @PostConstruct method at LocationResource");
    }

    @EJB
    private LocationBoundary locationBoundary;

    @GET
    @Path("/") //get all countries
    public Response getLocation() {
        List<String> list = locationBoundary.getCountries();
        return Response.ok(list).build();
    }

    @GET
    @Path("/{param}/cities") //get all cities by a country name
    public Response getCities(@PathParam("param") String country) {
        List<String> list =  locationBoundary.getCities(country);
        return Response.ok(list).build();
    }
}
