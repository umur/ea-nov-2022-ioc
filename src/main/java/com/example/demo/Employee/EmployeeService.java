package com.example.demo.Employee;

import com.example.demo.MyAutowired;
import com.example.demo.MyBean;

@MyBean
public class EmployeeService {
    @MyAutowired
    private EmployeeRepository repo;

    public Employee getEmployeeDetails(Integer empId)
    {
        return repo.getEmployeeById(empId);
    }
}
