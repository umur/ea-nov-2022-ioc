package com.vishwa.ea;


import java.lang.reflect.InvocationTargetException;

public class EaApplication {

	public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		MyInjector myInjector = new MyInjector();
		MyServiceImpl myService = myInjector.getBean(MyServiceImpl.class);
		System.out.println("My service is running or not ?? " + myService.isServiceRunning());
	}
}
