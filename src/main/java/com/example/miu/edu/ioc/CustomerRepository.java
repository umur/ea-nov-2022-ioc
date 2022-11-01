package com.example.miu.edu.ioc;
@MyBean
public class CustomerRepository {
    public String getCustomerName (String name) {
            return "Customer Name-" + name;

    }
}
