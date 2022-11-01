package com.example.demo.Employee;

import com.example.demo.MyBean;

import java.util.HashMap;
import java.util.Map;

@MyBean
public class EmployeeRepository {
    Map<Integer, Employee> Employees = new HashMap<>();

    public EmployeeRepository() {
        Employees.put(101, new Employee(101, "Bara 101 Batta", 120.0));
        Employees.put(112, new Employee(112, "Bara 112 Batta", 170.0));
        Employees.put(99, new Employee(99, "Bara 99 Batta", 180.0));
        Employees.put(45, new Employee(45, "Bara 45 Batta", 200.0));
    }

    public Employee getEmployeeById(Integer orderId) {
        return Employees.get(orderId);
    }
}
