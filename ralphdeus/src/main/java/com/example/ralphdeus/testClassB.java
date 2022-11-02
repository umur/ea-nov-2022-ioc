package com.example.ralphdeus;

@MyBean
public class testClassB {
    @MyAutoWired
    testClassB classB;

    public String testClassB(){
        return "Hello World from testClassB";
    }
}
