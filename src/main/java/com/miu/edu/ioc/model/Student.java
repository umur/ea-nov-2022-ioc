package com.miu.edu.ioc.model;

public class Student {
    private Person person;

    private double gpa;

    public Student() {}

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "person=" + person +
                ", gpa=" + gpa +
                '}';
    }
}
