package com.example.miu.edu.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IocApplication {

    public static void main (String[] args) {
        SpringApplication.run (IocApplication.class,args);
        CustomerController customerController = (CustomerController) MyInjector.getBean(CustomerController.class);
        System.out.println(customerController.getCustomerName("Alex"));
    }

}
