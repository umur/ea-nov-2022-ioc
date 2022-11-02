package org.example;

import org.example.annotations.MyAutowired;
import org.example.annotations.MyBean;

@MyBean
public class Department {
    @MyAutowired
    private Computer c;

    public void serve(){
        c.turnOn();
    }
}
