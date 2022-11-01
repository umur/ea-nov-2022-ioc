package com.waa.ioc;

@IoCBean // IoC Component marker
public class ClassA {
    @IoCAutoWired
    private  ClassB classB;

    public ClassA() {
    }
}
