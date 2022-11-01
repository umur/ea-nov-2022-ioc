package com.day1.ioc;

@MyBean
public class AClass {

    @MyAutowired
    BClass bClass;

    public boolean isSet(){
        return bClass != null;
    }
}
