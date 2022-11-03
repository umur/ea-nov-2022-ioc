package edu.miu.eaplayground.Lab1.sub;

import edu.miu.eaplayground.Lab1.MyAutowired;
import edu.miu.eaplayground.Lab1.MyBean;

@MyBean
public class Test {
    @MyAutowired
    private TestDetail testDetail;//new instance

    public void print(){
        System.out.println("Print in Test Class!!");
    }

    public void display(){
        testDetail.print();
    }
}
