package com.example.eanov2022ioc;

@MyBean
public class C {

    @MyAutowired
    public B b;

    public C(){
        System.out.println("constructor from C");
    }

    public void check(){
        b.check();
    }
}
