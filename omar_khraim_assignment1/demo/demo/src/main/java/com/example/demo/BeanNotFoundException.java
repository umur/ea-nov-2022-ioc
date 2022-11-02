package com.example.demo;

public class BeanNotFoundException extends Exception{
    public BeanNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
