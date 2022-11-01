package com.miu.edu.ioc.exception;

public class BeanNotFoundException extends RuntimeException {

    public BeanNotFoundException() {
    }

    public BeanNotFoundException(String className) {
        super("Bean not found: "  + className);
    }
}
