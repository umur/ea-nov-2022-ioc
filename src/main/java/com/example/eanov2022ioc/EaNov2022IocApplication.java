package com.example.eanov2022ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
public class EaNov2022IocApplication {

	public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {

//		SpringApplication.run(EaNov2022IocApplication.class, args);
		MyInjector injector = new MyInjector();
		CategoryController categoryController = injector.getBean(CategoryController.class);
		categoryController.print();
		categoryController.display();


	}

}
