package em;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("Hello, Ioc Container");
        MyInjector myInjector=new MyInjector();
        EAController eaController= myInjector.getBean(EAController.class);
        System.out.println("Is controller present?"+eaController.isStudentPresent());
    }
}
