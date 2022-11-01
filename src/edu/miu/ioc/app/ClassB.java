package edu.miu.ioc.app;

import edu.miu.ioc.core.annotation.MyAutowired;
import edu.miu.ioc.core.annotation.MyBean;

/**
 * Author: Kuylim TITH
 * Date: 10/31/2022
 */
@MyBean // comment this annotation to test bean not register
public class ClassB {

    @MyAutowired // comment this to test bean not proper inject
    private ClassA classA;

    public void showMessage() {
        classA.showMessage();
        System.out.println("This is class B");
    }
}
