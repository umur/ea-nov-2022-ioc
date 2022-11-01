package com.miu.edu.ioc.inject;

import com.miu.edu.ioc.MyApplication;
import com.miu.edu.ioc.annotation.MyAutowired;
import com.miu.edu.ioc.annotation.MyBean;
import com.miu.edu.ioc.exception.BeanNotFoundException;
import com.miu.edu.ioc.utils.ClassLoaderUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class MyInjector {
    private static Map<Class<?>, Object> beanMap = new HashMap<>();

    static {
        initialize();
    }

    private static void initialize() {
        try {
            initInstances(MyApplication.class);
        } catch (IllegalAccessException | ClassNotFoundException | IOException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param classz
     * @return the instance of MyBean annotation
     * @param <T> generic type
     */
    public static <T> T getBean(Class<T> classz) {
        return (T) getBeanInstance(classz);

    }

    /**
     *
     * @param interfaceClass
     * @return the instance of MyBean annotation
     * @param <T>
     * @throws BeanNotFoundException if the bean is not found
     */
    private static <T> Object getBeanInstance(Class<T> interfaceClass) throws BeanNotFoundException {
        if (beanMap.containsKey(interfaceClass)) {
            return beanMap.get(interfaceClass);
        } else {
            throw new BeanNotFoundException(interfaceClass.getName());
        }
    }

    /**
     *
     * @param mainClass
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static void initInstances(Class<?> mainClass)
            throws IllegalAccessException, ClassNotFoundException, IOException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Class<?>[] classes = ClassLoaderUtil.getClasses(mainClass.getPackage().getName());
        for (Class<?> classz : classes) {
            if (classz.isAnnotationPresent(MyBean.class)) {
                Object classInstance = classz.getConstructor().newInstance();
                beanMap.put(classz, classInstance);
                autowire(classz, classInstance);
            }
        }
    }

    private static void autowire(Class<?> classz, Object classInstance)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Field[] fields = classz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(MyAutowired.class)) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                beanMap.putIfAbsent(type, type.getConstructor().newInstance());
                Object fieldInstance = beanMap.get(type);
                field.set(classInstance, fieldInstance);
            }
        }
    }

}
