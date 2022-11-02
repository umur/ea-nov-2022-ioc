package com.miu.ea.eanov2022ioc;

import com.miu.ea.eanov2022ioc.annotation.BeanNotFoundException;
import com.miu.ea.eanov2022ioc.annotation.MyInjector;
import com.miu.ea.eanov2022ioc.test.Mock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
public class EaNov2022IocApplication {

    public static void main(String[] args) throws BeanNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        SpringApplication.run(EaNov2022IocApplication.class, args);
        MyInjector myInjector = new MyInjector();
        Mock mock = (Mock) myInjector.getBean(Mock.class);
        mock.print();
        mock.display();
    }

}
