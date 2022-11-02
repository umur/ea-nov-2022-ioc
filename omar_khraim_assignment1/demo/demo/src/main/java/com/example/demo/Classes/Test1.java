package com.example.demo.Classes;

import com.example.demo.MyAnnotations.MyAutowired;
import com.example.demo.MyAnnotations.MyBean;

@MyBean
public class Test1 {

    @MyAutowired
    Test1 test1;

    public void PrintTest1()
    {
        System.out.println("Test 1");
    }
}
