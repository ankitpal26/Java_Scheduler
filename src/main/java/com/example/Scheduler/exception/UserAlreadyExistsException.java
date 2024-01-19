package com.example.Scheduler.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String msg) {
        super(msg);
    }
    public UserAlreadyExistsException() {
        super();
    }
}
