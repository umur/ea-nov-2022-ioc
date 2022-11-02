package com.example.ralphdeus;

@MyBean
public class testClassA {
    @MyAutoWired
    testClassB classB;

    void TestClassA(){
        System.out.println("Test Call testClassA");
    }

    void atestClassA(){
        System.out.println(classB.testClassB());
    }
}
