package com.miu.edu.ioc.application;


import com.miu.edu.ioc.annotation.MyBean;
import com.miu.edu.ioc.model.Person;
import com.miu.edu.ioc.model.Student;

@MyBean
public class MyRepository {
    public Person getPersonInfo() {
        Person person = new Person();
        person.setName("Ba Luan Tran");
        person.setAge(31);
        return person;
    }

    public Student getStudentInfo() {
        Student student = new Student();
        Person person = getPersonInfo();
        student.setPerson(person);
        student.setGpa(4);
        return student;
    }
}
