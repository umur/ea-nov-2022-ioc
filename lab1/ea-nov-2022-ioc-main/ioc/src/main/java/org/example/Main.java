package org.example;

public class Main {
    public static void main(String[] args) throws Exception {

       Injector injector = new Injector();
        System.out.println("Hello world!");

        Employee employee=(Employee)injector.getBean("org.example.Employee");
        Address address=(Address) injector.getBean("org.example.Address");
        System.out.println(employee);
        System.out.println(address);
        System.out.println(employee.address);
    }
}