package src;

import src.annotations.MyAutowired;
import src.annotations.MyBean;

@MyBean

public class Test {
    @MyAutowired
    private static void name(String name){

        System.out.println(name);
    };


    public static void main(String[] args) throws Exception {

        MyInjector myInjector = new MyInjector();
        myInjector.searchBean();
        name("kojo");


        System.out.println(myInjector.getBean(Test.class));
    }
}
