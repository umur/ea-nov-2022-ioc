package com.miu.ea.eanov2022ioc.annotation;

public class BeanNotFoundException extends Exception {

    public BeanNotFoundException(String errorMessage){
        super(errorMessage);
    }

}
