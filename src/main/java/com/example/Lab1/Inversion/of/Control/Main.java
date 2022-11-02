package com.example.Lab1.Inversion.of.Control;



public class Main {
	public static void main(String args[]) throws InstantiationException, IllegalAccessException {
		MyInjector injector=new MyInjector();
		injector.setFieldDependency();

	       try {
	           injector.getBean(MessageTwo.class);
	       } catch (BeanNotFoundException e) {
	           throw new RuntimeException(e);
	       }
	}
}
