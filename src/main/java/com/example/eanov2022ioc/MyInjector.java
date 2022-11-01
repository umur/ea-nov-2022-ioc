package com.example.eanov2022ioc;

import org.reflections.Reflections;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class MyInjector {

    private final Map<Class<?>, Object> mapper = new HashMap<>();
    private final Reflections reflections = new Reflections("com.example.eanov2022ioc");

    public MyInjector() {

            getClasses().forEach( clazz -> {

                try {
                    Object currentInstance = getInstance(clazz);
                    getFields(clazz).forEach( field -> {
                        try {
                            Object fieldInstance = getInstance(field.getType());
                            field.set(currentInstance, fieldInstance);
                            mapper.put(field.getType(), fieldInstance);
                        }catch (NoSuchMethodException | InvocationTargetException | RuntimeException | InstantiationException | IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    mapper.put(clazz, currentInstance);
                } catch (NoSuchMethodException | InvocationTargetException | RuntimeException | InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }


            });
    }

    private Set<Class<?>> getClasses() {
        return reflections.getTypesAnnotatedWith(MyBean.class);
    }

    private Set<Field> getFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(MyAutowired.class)) // only MyAutowired annotated fields
                .collect(Collectors.toSet());
    }

    private Object getInstance(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try {
            return getBean(clazz);
        }catch (BeanNotFoundException e){
            return clazz.getDeclaredConstructor().newInstance();
        }
    }

    public Object getBean(Class<?> clazz) throws BeanNotFoundException {
        if (Objects.nonNull(mapper.get(clazz))) {
            return mapper.get(clazz);
        } else {
            throw new BeanNotFoundException("Bean not found for class " + clazz.getSimpleName());
        }
    }
}
