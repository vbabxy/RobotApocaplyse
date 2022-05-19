package com.robot.robotapocalypse.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ModelAlreadyExistException extends RuntimeException{

    private String className;
    private String argumentSupplied;
    private String errorCode;

    public ModelAlreadyExistException(String className, String argumentSupplied, String errorCode) {
        super(argumentSupplied);
        this.className = className;
        this.argumentSupplied = argumentSupplied;
        this.errorCode = errorCode;
    }

    public ModelAlreadyExistException(String className, String argumentSupplied) {
        super(argumentSupplied);
        this.className = className;
        this.argumentSupplied = argumentSupplied;
    }

    public ModelAlreadyExistException(String argumentSupplied) {
        super(argumentSupplied);
        this.argumentSupplied = argumentSupplied;
    }
}
