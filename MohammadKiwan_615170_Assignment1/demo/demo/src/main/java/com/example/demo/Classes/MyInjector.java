package com.example.demo.Classes;

import com.example.demo.MyAnnotations.MyAutowired;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class MyInjector {

    static public HashMap<Class,Object> ScanClasses() {

        HashMap<Class,Object> hashMap = new HashMap<>();
        Reflections reflections = new Reflections("com/example/demo/Classes", new SubTypesScanner(false));
        Set<Class> x = reflections.getSubTypesOf(Object.class)
                .stream()
                .collect(Collectors.toSet());


        for (Class c : x) {
            for (Field field : c.getDeclaredFields()) {
                if (field.getDeclaredAnnotationsByType(MyAutowired.class).length > 0) {
                    try {
                        Object obj = Class.forName(c.getName()).newInstance();;
                        hashMap.put(c,obj);
                    } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return hashMap;

    }
}
