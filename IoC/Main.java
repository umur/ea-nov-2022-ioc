package IoC;

import classes.A;
import classes.B;
import classes.C;

public class Main {
    @MyAutowired
    private String n;
    public static void main(String[] args) {
        MyInjector inj = new MyInjector("classes");
        try {
            C a = inj.getBean(C.class);
            System.out.println(a);
        } catch (BeanNotFoundException ignore) {

        }
    }
}
