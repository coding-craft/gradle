package com.student.service.exception;

public class StudentUpdateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public StudentUpdateException(String msg) {
        super(msg);
    }
}
