package com.afse.academy.mapper;

import com.afse.academy.exception.InvalidInputException;

import javax.ejb.EJBException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * This is an exception mapper for the hr-application. It catches all the exceptions of the hibernate validation
 * and all the invalid input exceptions.
 */
@Provider
public class HRAppExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {

        if (e instanceof InvalidInputException) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        if (e instanceof EJBException) {
            if (e.getCause() instanceof ConstraintViolationException) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(prepareMessage((ConstraintViolationException) e.getCause()))
                        .type("text/plain")
                        .build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).entity("The entity is Not Found").build();
    }

    private String prepareMessage(ConstraintViolationException exception) {
        StringBuilder msg = new StringBuilder();
        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
            msg.append(cv.getPropertyPath()).append(" ").append(cv.getMessage()).append("\n");
        }
        return msg.toString();
    }
}