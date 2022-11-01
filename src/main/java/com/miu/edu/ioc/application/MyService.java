package com.miu.edu.ioc.application;

import com.miu.edu.ioc.annotation.MyAutowired;
import com.miu.edu.ioc.annotation.MyBean;
import com.miu.edu.ioc.model.Person;
import com.miu.edu.ioc.model.Student;

@MyBean
public class MyService {

    @MyAutowired
    private MyRepository myRepository;

    public Person getPersonInfo() {
        return myRepository.getPersonInfo();
    }

    public Student getStudentInfo() {
        return myRepository.getStudentInfo();
    }
}
