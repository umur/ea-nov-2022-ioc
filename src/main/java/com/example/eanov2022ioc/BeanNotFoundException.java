package com.example.eanov2022ioc;

public class BeanNotFoundException extends Exception{
    public BeanNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
