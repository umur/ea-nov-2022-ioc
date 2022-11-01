package com.example.main;

import java.lang.reflect.InvocationTargetException;

public class MainApplication {
    public static void main(String[] args) throws BeanNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        MyInjector myInjector = new MyInjector();
        myInjector.searchBeans();
        myInjector.fieldInjection();

        A a = (A) myInjector.getBeans(A.class);
        System.out.println(a);

    }
}
