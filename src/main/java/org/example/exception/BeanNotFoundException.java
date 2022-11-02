package org.example.exception;

public class BeanNotFoundException extends RuntimeException{
    public BeanNotFoundException(String msg){
        super(msg);
    }
}
