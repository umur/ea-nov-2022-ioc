package org.example;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.example.annotations.MyAutowired;
import org.example.annotations.MyBean;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import java.util.*;
public class Injector {
    private Map<String, Object> maps = new HashMap<>();

    Injector() throws Exception {

        inject();

    }

    private void inject() throws Exception {

        ConfigurationBuilder beansBuilder = new ConfigurationBuilder();
        beansBuilder.setScanners(new TypeAnnotationsScanner(),new SubTypesScanner());
        beansBuilder.setUrls(ClasspathHelper.forPackage("."));
        Reflections beansReflections = new Reflections(beansBuilder);
        Set<Class<?>> classes = beansReflections.getTypesAnnotatedWith(MyBean.class);

        for (Class<?> c: classes) {
                 String temp=c.getTypeName();

                 maps.put(temp,c.getDeclaredConstructor().newInstance());
        }
        maps.forEach((k,v)->{
            Field[] fields = v.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getDeclaredAnnotationsByType(MyAutowired.class).length > 0) {
                    String fieldName = field.getType().getTypeName();
                    Object fieldObj = maps.get(fieldName);
                    if (fieldObj == null) {

                        throw new RuntimeException();
                    }

                    field.setAccessible(true);
                    try {
                        field.set(v, fieldObj);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
         maps.forEach((k,v)->System.out.println( k + "->" + v));

    }


    public Object getBean (String typeName){
        return maps.get(typeName);
    }

}