package miu.edu.ioc;

public class BeanNotFoundException extends RuntimeException {

    public BeanNotFoundException(String message) {
        super(message);
    }
}
