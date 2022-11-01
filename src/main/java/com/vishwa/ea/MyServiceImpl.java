package com.vishwa.ea;

@MyBean
public class MyServiceImpl {
    @MyAutowired
    MyService myService;

    public String isServiceRunning(){
        return myService != null ? "Yes, MyService is running!!! " : "Nope, try again!!!";
    }
}
