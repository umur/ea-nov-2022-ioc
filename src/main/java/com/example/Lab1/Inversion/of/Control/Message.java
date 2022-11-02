package com.example.Lab1.Inversion.of.Control;


public class Message {
	  @MyAutowired
	    private MessageTwo messageTwo;

	    @Override
	    public String toString() {
	        return "messageOne" ;
	    }
}
