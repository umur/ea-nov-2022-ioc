package com.day1.ioc;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyInjector {
    private Map<String, Object> maps = new HashMap<>();

    MyInjector() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        run();
    }

    void run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //search through pkg
        ConfigurationBuilder confBuilder = new ConfigurationBuilder();
        //find class with annotation
        confBuilder.setScanners(Scanners.TypesAnnotated); // only scan for types/class annotation
        confBuilder.setUrls(ClasspathHelper.forPackage("day1"));

        Reflections reflections = new Reflections(confBuilder);
        Set<Class<?>> setts = reflections.getTypesAnnotatedWith(MyBean.class);

        for (Class<?> sett : setts) {
            Object obj = getFromSet(sett, sett.getDeclaredConstructor().newInstance());
            maps.put(sett.getSimpleName(), obj);

            Field[] fieldList = sett.getDeclaredFields();

            for (Field f : fieldList) {
                if (f.getDeclaredAnnotationsByType(MyAutowired.class).length > 0) {
                    Class<?> fClass = f.getType();
                    Object oj = getFromSet(fClass, fClass.getDeclaredConstructor().newInstance());
                    //put it in map
                    maps.put(fClass.getSimpleName(), oj);
                    f.set(obj, oj);
                }
            }
        }
    }

    private Object getFromSet(Class<?> set, Object value) {
        return maps.getOrDefault(set.getSimpleName(), value);
    }

    public <T> T getBeans(Class<T> cls) {
        T t = (T) maps.get(cls.getSimpleName());
        if (t == null) throw new RuntimeException("NOT FOUND");
        return t;
    }
}
