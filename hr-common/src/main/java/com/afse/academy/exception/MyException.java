package com.afse.academy.exception;

public class MyException extends  Exception {
    public MyException() {}
    public MyException(String msg) {
        super(msg);
    }
    public MyException(Exception e) {
        super(e.getMessage());
    }
}