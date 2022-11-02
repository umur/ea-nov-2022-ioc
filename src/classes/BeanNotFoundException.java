package classes;

public class BeanNotFoundException extends Exception{

    public BeanNotFoundException()
    {
        super();
    }

    public BeanNotFoundException(String message){
        super(message);
    }

    public BeanNotFoundException(Throwable t){
        super(t);
    }
}
