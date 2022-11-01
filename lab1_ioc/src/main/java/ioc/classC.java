package ioc;

public class classC {
    @MyAutowired
    boolean testfieldC = true;

    public void printClass(){
        System.out.println("Printing from class C");
    }
}
