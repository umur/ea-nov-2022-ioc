package com.example.eanov2022ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EaNov2022IocApplication {

    public static void main(String[] args) {

        SpringApplication.run(EaNov2022IocApplication.class, args);
        MyInjector injector = new MyInjector();

        A a = (A) injector.getBean(A.class);

        C c = (C) injector.getBean(C.class);
        c.check();

        try {
            D d =  (D) injector.getBean(D.class);
        }catch (BeanNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

}
