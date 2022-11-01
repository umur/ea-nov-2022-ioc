package com.waa.ioc;

public class Main {

	public static void main(String[] args) throws IoCBeanNotFoundException {
		// kick on IoC injector
	IoCBeansInjector ioCBeansInjector = new IoCBeansInjector();
	ioCBeansInjector.printIoCManagedBeans();

	ioCBeansInjector.getClass(ioCBeansInjector.getClass());
	}

}
