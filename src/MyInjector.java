import demo.ioc.ComponentScan;
import demo.ioc.Configuration;
import demo.ioc.MyAutowired;
import demo.ioc.MyBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;


public class MyInjector {

    Map<Class<?>, Object> iocRegistery = new HashMap<>();

    MyInjector(Class<?> clazz) {
        initializeContext(clazz);
    }

    private void initializeContext(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Configuration.class)) {
            throw new RuntimeException("Please provide a valid configuration file!");
        } else {
            ComponentScan componentScan = clazz.getAnnotation(ComponentScan.class);
            String packageValue = componentScan.value();
            Set<Class<?>> classes = findClasses(packageValue);

            for (Class<?> loadingClass : classes) {
                try {
                    if (loadingClass.isAnnotationPresent(MyBean.class)) {
                        Constructor<?> constructor = loadingClass.getDeclaredConstructor();
                        Object newInstance = constructor.newInstance();
                        iocRegistery.put(loadingClass, newInstance);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Set<Class<?>> findClasses(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T getBean(Class<T> clazz) throws Exception {
//        Constructor<?> constructor = clazz.getConstructor();
        T object = (T) iocRegistery.get(clazz);

        Field[] declaredFields = clazz.getDeclaredFields();
        injectAnnotatedFields(object, declaredFields);

        return object;
    }

    private <T> void injectAnnotatedFields(T object, Field[] declaredFields) throws Exception {
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(MyAutowired.class)) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                Object innerObject = iocRegistery.get(type);

                System.out.println(object);
                System.out.println(innerObject);

                field.set(object, innerObject);
                injectAnnotatedFields(innerObject, type.getDeclaredFields());
            }
        }
    }

}