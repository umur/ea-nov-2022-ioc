package com.example.lab1;


public class Lab1Application {

	public static void main(String[] args) throws Exception {

		MyInjector injector = new MyInjector();
		Person person = (Person) injector.getBeans(Person.class);
		Address address = (Address) injector.getBeans(Address.class);

		System.out.println(person);
		System.out.println(address);
		System.out.println(person.address);

	}
}
