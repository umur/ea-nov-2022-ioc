package com.example.demo;

import com.example.demo.Employee.EmployeeRepository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MyInjector {
    Map<Class<?>, Object> objectsMap = new HashMap<>();

    MyInjector(Class<?> clazz) throws BeanNotFoundException {
        initializeContext(clazz);
    }

    public <T> T getBean(Class<T> clazz) throws Exception {
        T object = (T) objectsMap.get(clazz);

        Field[] declaredFields = clazz.getDeclaredFields();
        injectAnnotatedFields(object, declaredFields);

        return object;
    }

    private <T> void injectAnnotatedFields(T object, Field[] declaredFields) throws IllegalAccessException {
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(MyAutowired.class)) {
                field.setAccessible(true);
                Class<?> type = field.getType();
//                System.out.println(type.getName());
//                objectsMap.forEach((x,y)-> System.out.println(x.getName()));
                Object innerObject = objectsMap.get(type);

                field.set(object, innerObject);
                injectAnnotatedFields(innerObject, type.getDeclaredFields());
            }
        }
    }

    private void initializeContext(Class<?> clazz) throws BeanNotFoundException {
        if (!clazz.isAnnotationPresent(BattaConfig.class)) {
            throw new BeanNotFoundException();
        } else {
            ComponentScan componentScan = clazz.getAnnotation(ComponentScan.class);
            String packageValue = componentScan.value();
//            System.out.println("PackageVal: "+packageValue);
            Set<Class<?>> classes = findClasses(packageValue);

            for (Class<?> loadingClass : classes) {

                try {
                    if (loadingClass.isAnnotationPresent(MyBean.class)) {
//                        System.out.println("if is true "+loadingClass);
                        Constructor<?> constructor = loadingClass.getDeclaredConstructor();
                        Object newInstance = constructor.newInstance();
                        objectsMap.put(loadingClass, newInstance);
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
}