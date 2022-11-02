package com.miu.ea.eanov2022ioc.test;

import com.miu.ea.eanov2022ioc.annotation.MyAutowired;
import com.miu.ea.eanov2022ioc.annotation.MyBean;

@MyBean
public class Mock {

    @MyAutowired
    MockDetail mockDetail;

    public void print(){
        System.out.println("Print function in the Mock class.");
    }

    public void display(){
        mockDetail.print();
    }
}
