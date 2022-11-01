package com.example.demo;

public class BeanNotFoundException extends Exception {
    BeanNotFoundException(){
        System.out.println("Bean Not Found in Map Exception");
    }
}
