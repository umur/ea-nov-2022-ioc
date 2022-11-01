package classes;

import IoC.MyAutowired;
import IoC.MyBean;

@MyBean
public class A {

    @MyAutowired
    private B b;
}
