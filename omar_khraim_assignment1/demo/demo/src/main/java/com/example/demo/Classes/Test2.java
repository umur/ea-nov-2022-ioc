package com.example.demo.Classes;

import com.example.demo.MyAnnotations.MyAutowired;
import com.example.demo.MyAnnotations.MyBean;

@MyBean

public class Test2 {

    @MyAutowired
    Test2 test2;

    public void PrintTest2()
    {
        System.out.println("Test 2");
    }
}
