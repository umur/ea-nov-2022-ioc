package classes;

import IoC.MyAutowired;
import IoC.MyBean;

@MyBean
public class C {
    @MyAutowired
    private B b;
}
