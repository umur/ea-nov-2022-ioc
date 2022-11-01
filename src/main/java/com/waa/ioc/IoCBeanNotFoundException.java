package com.waa.ioc;

public class IoCBeanNotFoundException extends Exception {
    public IoCBeanNotFoundException(Class cls ) {
        throw new RuntimeException(cls + " BeanNotFoundException");
    }
}
