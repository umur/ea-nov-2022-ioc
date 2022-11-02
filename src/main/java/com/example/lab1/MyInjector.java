package com.example.lab1;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.util.*;

public class MyInjector {
    private Map<String, Object> map = new HashMap<>();

    MyInjector() throws Exception {
        inject();
    }

    private void inject() throws Exception {
        ConfigurationBuilder beanBuilder = new ConfigurationBuilder();
        beanBuilder.setScanners(Scanners.TypesAnnotated);
        beanBuilder.setUrls(ClasspathHelper.forPackage("."));

        Reflections beanReflections = new Reflections(beanBuilder);
        Set<Class<?>> classes = beanReflections.getTypesAnnotatedWith(MyBean.class);
        for (Class<?> c : classes) {
            Object object = getFromMap(c, c.getDeclaredConstructor().newInstance());
            putIntoMap(c, object);
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                if(field.getDeclaredAnnotationsByType(MyAutowired.class).length > 0){
                    Class<?> fieldClass = field.getType();
                    Object fieldObject = getFromMap(fieldClass, fieldClass.getDeclaredConstructor().newInstance());
                    putIntoMap(fieldClass, fieldObject);
                    field.set(object, fieldObject);
                }
            }
        }
    }

    private Object getFromMap(Class<?> c, Object defaultValue) {
        return map.getOrDefault(c.getSimpleName(), defaultValue);
    }

    private void putIntoMap(Class<?> c, Object o){
        map.put(c.getSimpleName(), o);
    }

    public <T> T getBeans(Class<T> tClass){
        T o = (T)getFromMap(tClass, null);
        if (o == null){
            throw new BeanNotFoundException();
        }
        return o;
    }

}






















