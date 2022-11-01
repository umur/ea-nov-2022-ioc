package com.waa.ioc;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class IoCBeansInjector {
    // object container
    Map<String, Object> beans = new HashMap<>();

    public IoCBeansInjector() {
        // scan classes and collect Beans
        // get current class package and use it as a base directory
        Set<Class> classes = findAllClassesUsingClassLoader(getClass().getPackageName());
        classes.forEach(cls -> {
            if (cls.getAnnotation(IoCBean.class) != null) {
                try {
                    beans.put(cls.getName(),
                            cls.getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    public Object getClass(Class cls) throws IoCBeanNotFoundException {
        if (beans.containsKey(cls)) {
            return beans.get(cls);
        } else throw new IoCBeanNotFoundException(cls);
    }

    public void printIoCManagedBeans() {
        this.beans.forEach((cls, instance) -> System.out.println("Class: " + cls + ", Instance: " + instance));
    }

    // get classes using JRE's classLoader
    public Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader
                .getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader
                .lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class
                    .forName(packageName + "."
                            + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
            System.out.println(e.getMessage());
        }
        return null;
    }

    // get classes using reflections library
    public Set<Class> findAllClassesUsingReflectionsLibrary(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return reflections.getSubTypesOf(Object.class)
                .stream()
                .collect(Collectors.toSet());
    }

}
