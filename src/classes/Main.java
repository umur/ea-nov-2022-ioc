package classes;

public class Main {
    public static void main(String[] args){
        MyInjector injector = new MyInjector();
        injector.scanAnnotations();
        try{
            //myInjector.getBean(ClassA.class);
            Object bean = injector.getBean(A.class);
            System.out.println("Object found: " + bean);
        }
        catch (BeanNotFoundException exception)
        {
            System.out.println("Object not found");
        }
    }
}
