package rajiv;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        MyInjector injector = new MyInjector();

        PersonController personController = injector.getBean(PersonController.class);
        System.out.println("PetController is set inside? " + personController.isPetSet());
    }
}
