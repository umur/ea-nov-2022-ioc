package com.example.demo;

import com.example.demo.Employee.Employee;
import com.example.demo.Employee.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		try {
			MyInjector applicationContext = new MyInjector(Config.class);
			EmployeeService employeeService = applicationContext.getBean(EmployeeService.class);
			Employee order = employeeService.getEmployeeDetails(99);
			System.out.println(order);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
