package com.example.eanov2022ioc;

@MyBean
public class B {

    public B(){
        System.out.println("constructor from B");
    }

    public void check(){
        System.out.println("checking instance of B");
    }
}
