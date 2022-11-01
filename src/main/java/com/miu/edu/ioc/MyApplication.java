package com.miu.edu.ioc;

import com.miu.edu.ioc.application.MyController;
import com.miu.edu.ioc.inject.MyInjector;

public class MyApplication {

    public static void main(String[] args) {
        MyController myController = MyInjector.getBean(MyController.class);
        myController.displayPerson();
        myController.displayStudent();
    }

}
