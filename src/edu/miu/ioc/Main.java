package edu.miu.ioc;

import edu.miu.ioc.app.ClassC;
import edu.miu.ioc.core.MyInjector;
import edu.miu.ioc.core.InversionMap;

public class Main {
    // scan package & inject beans
    static {
        MyInjector.registerBeans(Main.class.getPackageName());
        MyInjector.validAndInjectBeans(Main.class.getPackageName());
    }

    public static void main(String[] args) {
        ClassC classC = (ClassC) InversionMap.BEANS.get("edu.miu.ioc.app.ClassC");
        classC.showMessage();
    }
}