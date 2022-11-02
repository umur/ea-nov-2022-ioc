package com.example.lab1;

@MyBean
public class Person {
    private String firstName;
    private String lastName;

    @MyAutowired
    public Address address;
}
