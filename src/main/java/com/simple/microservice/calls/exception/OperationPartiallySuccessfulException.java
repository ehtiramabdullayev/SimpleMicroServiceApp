package com.simple.microservice.calls.exception;

public class OperationPartiallySuccessfulException extends RuntimeException {

    public OperationPartiallySuccessfulException(String message) {
        super(message);
    }

    public OperationPartiallySuccessfulException(String message, Throwable cause) {
        super(message, cause);
    }
}
