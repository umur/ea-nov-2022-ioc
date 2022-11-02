package com.example.Lab1.Inversion.of.Control;



public class MessageThree {
	 @MyAutowired
	    private MessageTwo messageTwo;

	    @Override
	    public String toString() {
	        return messageTwo.testFunction() ;
	    }
}
