package com.example.ralphdeus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RalphdeusApplication {

	public static void main(String[] args) {

		MyInjector inj = new MyInjector();
		inj.AutoWiredObjects();

		try{
			inj.getBean(testClassA.class);
			System.out.println("we found the OBJ from Inversion of Control");
		}
		catch (Exception ex)
		{
			System.out.println(ex.toString());
		}

		SpringApplication.run(RalphdeusApplication.class, args);
	}

}
