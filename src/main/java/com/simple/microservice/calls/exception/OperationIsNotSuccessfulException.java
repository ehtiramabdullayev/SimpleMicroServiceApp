package com.simple.microservice.calls.exception;

public class OperationIsNotSuccessfulException extends RuntimeException{

    public OperationIsNotSuccessfulException(String message) {
        super(message);
    }

    public OperationIsNotSuccessfulException(String message, Throwable cause) {
        super(message, cause);
    }
}
