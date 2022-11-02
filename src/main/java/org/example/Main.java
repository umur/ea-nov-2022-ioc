package org.example;

import org.example.injector.MyInjector;

public class Main {

    public static void main(String[] args) throws Exception {
        MyInjector.start(Main.class);
        Department department = (Department) MyInjector.getBean(Department.class);
        department.serve();
    }
}