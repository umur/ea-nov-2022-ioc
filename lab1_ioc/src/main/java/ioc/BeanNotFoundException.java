package ioc;

public class BeanNotFoundException extends RuntimeException {
    public BeanNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
