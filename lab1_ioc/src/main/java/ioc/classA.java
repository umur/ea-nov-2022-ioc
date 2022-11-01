package ioc;

public class classA {
    @MyAutowired
    boolean testfieldA = true;

    public void printClass(){
        System.out.println("Printing from class A");
    }
}
