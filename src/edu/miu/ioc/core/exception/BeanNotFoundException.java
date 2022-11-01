package edu.miu.ioc.core.exception;

/**
 * Author: Kuylim TITH
 * Date: 10/31/2022
 */
public class BeanNotFoundException extends RuntimeException {
    public BeanNotFoundException(String s) {
        super(s);
    }
}
