package com.robot.robotapocalypse.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BadRequestException extends RuntimeException{

    private String className;
    private String argumentSupplied;
    private String errorCode;

    public BadRequestException(String className, String argumentSupplied, String errorCode) {
        super(argumentSupplied);
        this.className = className;
        this.argumentSupplied = argumentSupplied;
        this.errorCode = errorCode;
    }

    public BadRequestException(String className, String argumentSupplied) {
        super(argumentSupplied);
        this.className = className;
        this.argumentSupplied = argumentSupplied;
    }

    public BadRequestException(String argumentSupplied) {
        super(argumentSupplied);
        this.argumentSupplied = argumentSupplied;
    }
}
