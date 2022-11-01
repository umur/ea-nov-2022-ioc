package com.example.main;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MyInjector {
    static Map<Class, Object> annotationsMap = new HashMap<>();

    Set<Field> fields;

    public void searchBeans() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Reflections reflections = new Reflections("", new SubTypesScanner(), new TypeAnnotationsScanner(), new FieldAnnotationsScanner());

        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(MyBean.class);
        fields = reflections.getFieldsAnnotatedWith(MyAutowired.class);

        for (Class<?> classes : annotatedClasses) {
            Object c = classes.getDeclaredConstructor().newInstance();
            annotationsMap.put(classes, c);
        }
    }

    public Object getBeans(Class<?> declaringClass) throws BeanNotFoundException {
        if (annotationsMap.containsKey(declaringClass)) {
            return annotationsMap.get(declaringClass);
        } else {
            throw new BeanNotFoundException();
        }
    }
    public void fieldInjection() throws IllegalAccessException {
        for (Field field : fields) {
            Object className = field.getDeclaringClass();
            Object fieldType = field.getType();

            Object classObject = annotationsMap.get(className);
            Object fieldObject = annotationsMap.get(fieldType);

            field.setAccessible(true);
            field.set(classObject, fieldObject);
        }
    }
}
