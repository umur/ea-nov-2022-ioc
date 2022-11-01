package com.miu.ea;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IocApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(IocApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MyInjector myInjector = new MyInjector();
		String curPackageName = myInjector.getClass().getPackageName(); //"com.miu.ea"
		System.out.println("MyInjector creates instances for classes with annotation MyBean:");
		myInjector.createInstances(curPackageName);
		System.out.println("MyInjector createInstances finished!");
		System.out.println("------------------------------------");

		System.out.println("Get bean method is invoked for : MyTestClassA" + myInjector.getBean(Class.forName("com.miu.ea.MyTestClassA")));
		System.out.println("Get bean method is invoked for : MyTestClassB" + myInjector.getBean(Class.forName("com.miu.ea.MyTestClassB")));
	}
}
