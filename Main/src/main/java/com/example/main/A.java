package com.example.main;

@MyBean
public class A {
    @MyAutowired
    B b;

    @Override
    public String toString() {
        return "This is " + b + " inside A class";
    }
}
