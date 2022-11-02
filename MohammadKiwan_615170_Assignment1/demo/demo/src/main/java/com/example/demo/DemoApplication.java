package com.example.demo;

import com.example.demo.Classes.MyInjector;
import com.example.demo.Classes.Test1;
import com.example.demo.Classes.Test2;
import com.example.demo.Classes.Test3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class DemoApplication {
	static HashMap<Class,Object> myClasses = MyInjector.ScanClasses();

	public static Object getBean(Class clazz) throws BeanNotFoundException {

		if(myClasses.containsKey(clazz))
			return myClasses.get(clazz);
		else
			throw new BeanNotFoundException("Bean Not Found");


	}
	public static void main(String[] args) {
		try {
			Object o = getBean(Test1.class);
			System.out.println("Obj of Test1 Found");
			Object o2 = getBean(Test2.class);
			System.out.println("Obj of Test2 Found");

			//this will throw error
			Object o3 = getBean(Test3.class);
		} catch (BeanNotFoundException e) {
			throw new RuntimeException(e);
		}
		SpringApplication.run(DemoApplication.class, args);

	}

}
