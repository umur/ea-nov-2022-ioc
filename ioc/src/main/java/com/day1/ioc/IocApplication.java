package com.day1.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
public class IocApplication {

	public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//		SpringApplication.run(IocApplication.class, args);
		MyInjector myInjector = new MyInjector();

		AClass aClass = myInjector.getBeans(AClass.class);
		System.out.println("Output:"+ aClass.isSet());

	}

}
