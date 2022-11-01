package com.miu.edu.ioc.application;

import com.miu.edu.ioc.annotation.MyAutowired;
import com.miu.edu.ioc.annotation.MyBean;

@MyBean
public class MyController {

    @MyAutowired
    private MyService service;

    public void displayPerson() {
        System.out.println(service.getPersonInfo());
    }

    public void displayStudent() {
        System.out.println(service.getStudentInfo());
    }
}
