package com.afse.academy;

import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

/**
 *
 * This is a Generic class for formatting the response of all the requests.
 * @param <T> Serializable
 */
public class GenericResponse<T extends Serializable> {

    private boolean result;
    private String message;
    private Response.Status status;
    private T t;
    private List<T> list;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response.Status getStatus() { return status; }

    public void setStatus(Response.Status status) {
        this.status = status;
    }

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list){
        this.list = list;
    }

    @Override
    public String toString() {
        return ((result + " | " + message + " | " + status + " | " + t).toUpperCase());
    }
}