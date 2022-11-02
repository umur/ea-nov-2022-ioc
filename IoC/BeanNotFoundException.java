package IoC;

public class BeanNotFoundException extends Exception{
    public BeanNotFoundException() {
        super("Bean not found in the project");
    }

    public BeanNotFoundException(String s) {
        super(s);
    }

    public BeanNotFoundException(Throwable t) {
        super(t);
    }

}
