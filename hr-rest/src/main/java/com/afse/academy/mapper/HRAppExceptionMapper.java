package com.afse.academy.mapper;

import com.afse.academy.MyException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

//@Provider
public class HRAppExceptionMapper implements ExceptionMapper <Exception>{
    @Override
    public Response toResponse(Exception e) {
        if(e instanceof MyException) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Failure").build();
        }
        return null;
    }
}
