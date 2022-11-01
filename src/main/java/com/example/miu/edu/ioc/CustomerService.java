package com.example.miu.edu.ioc;
@MyBean
public class CustomerService {
    @MyAutowired
    private CustomerRepository customerRepository;

    public String getCustomerName (String name) {
        return this.customerRepository.getCustomerName(name);
    }
}
