package com.example.miu.edu.ioc;
@MyBean
public class CustomerController {
    @MyAutowired
    private CustomerService customerService;

    public String getCustomerName(String name) {
        return customerService.getCustomerName(name);
    }
}
