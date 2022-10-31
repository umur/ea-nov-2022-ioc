package miu.edu.ioc;

import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class MyInjector {
    private final Map<Class<?>, Object> instances = new HashMap<>();
    private final Reflections reflections = new Reflections("miu.edu.ioc");
    public MyInjector() {
        try {
            for (Class<?> clazz : getClasses()) {
                Object currentInstance = getInstance(clazz);
                for(Field field : getFields(clazz)) {
                    Object fieldInstance = getInstance(field.getType());
                    field.set(currentInstance, fieldInstance);
                    instances.put(field.getType(), fieldInstance);
                }
                instances.put(clazz, currentInstance);
            }
        } catch (InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    Set<Class<?>> getClasses() {
        return reflections.getTypesAnnotatedWith(MyBean.class);
    }

    Set<Field> getFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(MyAutowired.class)) // only MyAutowired annotated fields
                .collect(Collectors.toSet());
    }

    Object getInstance(Class<?> clazz) throws NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException {
        try  {
            return getBean(clazz); // if already exist
        } catch (BeanNotFoundException e) {
            return clazz.getDeclaredConstructor().newInstance();
        }
    }

    Object getBean(Class<?> clazz) {
        if (Objects.nonNull(instances.get(clazz))) {
            return instances.get(clazz);
        } else {
            throw new BeanNotFoundException(String.format("%s class not found", clazz.getSimpleName()));
        }
    }
}
