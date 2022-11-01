package edu.miu.ioc.app;

import edu.miu.ioc.core.annotation.MyAutowired;
import edu.miu.ioc.core.annotation.MyBean;

/**
 * Author: Kuylim TITH
 * Date: 10/31/2022
 */
@MyBean
public class ClassC {

    @MyAutowired
    private ClassB classB;

    public void showMessage() {
        classB.showMessage();
        System.out.println("This is class C");
    }
}
