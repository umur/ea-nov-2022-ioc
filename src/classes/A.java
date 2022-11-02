package classes;

import annotation.MyAutowired;
import annotation.MyBean;

@MyBean
public class A {

    @MyAutowired
    private B b;
}
