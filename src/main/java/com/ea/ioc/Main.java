package com.ea.ioc;

public class Main {

	public static void main(String[] args) throws IoCBeanNotFoundException {
		// kick on IoC injector
	IoCBeansInjector ioCBeansInjector = new IoCBeansInjector();
	ClassA classA = new ClassA();
	ioCBeansInjector.printIoCManagedBeans();

		System.out.println(ioCBeansInjector.getClass(classA.getClass()));
		//ioCBeansInjector.getClass(ioCBeansInjector.getClass());
	}

}
