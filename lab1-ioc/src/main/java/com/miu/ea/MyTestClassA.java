package com.miu.ea;

@MyBean
public class MyTestClassA {
    @MyAutowired
    private MyTestClassB b;

    private void print(){
        System.out.println("This is MyTestClassA");

        System.out.println("Here b object is used:");
        b.print();
    }
}
