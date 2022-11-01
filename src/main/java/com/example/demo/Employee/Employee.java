package com.example.demo.Employee;

import com.example.demo.BattaConfig;
import com.example.demo.MyBean;


public class Employee {
    private final Integer id;
    private final String name;
    private final Double salary;

    public Employee(){
        this.id =1;
        this.name = null;
        this.salary = 0.0;
    }
    public Employee(Integer id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}
