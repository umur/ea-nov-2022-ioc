package edu.miu.ioc;

import edu.miu.ioc.app.ClassC;
import edu.miu.ioc.core.BeanConfiguration;
import edu.miu.ioc.core.InversionContainer;

public class Main {
    // scan package & inject beans
    static {
        BeanConfiguration.registerBeans(Main.class.getPackageName());
        BeanConfiguration.validAndInjectBeans(Main.class.getPackageName());
    }

    public static void main(String[] args) {
        ClassC classC = (ClassC) InversionContainer.BEANS.get("edu.miu.ioc.app.ClassC");
        classC.showMessage();
    }
}