package com.afse.academy.exception;

public class InvalidInputException extends Exception {

    public InvalidInputException() {}

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(Exception e) {
        super(e.getMessage());
    }
}