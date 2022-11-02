package classes;

import annotation.MyAutowired;
import annotation.MyBean;

@MyBean
public class B {

    @MyAutowired
    private C c;
}
