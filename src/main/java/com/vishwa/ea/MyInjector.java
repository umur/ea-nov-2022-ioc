package com.vishwa.ea;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

public class MyInjector {
    private Map<String, Object> objMap = new HashMap<>();

    public MyInjector() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        injector();
    }

    public void injector() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setScanners(Scanners.TypesAnnotated);
        configurationBuilder.setUrls(ClasspathHelper.forPackage("com.vishwa.ea"));

        Reflections reflections = new Reflections(configurationBuilder);
        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(MyBean.class);

        for (Class<?> cl : classSet) {
            System.out.println(cl.getSimpleName());
            Object obj = objMap.getOrDefault(cl.getSimpleName(), cl.getDeclaredConstructor().newInstance());
            System.out.println(obj);
            objMap.put(cl.getSimpleName(), obj);
            Field[] fields = cl.getDeclaredFields();

            for (Field fld : fields) {
                if (fld.getDeclaredAnnotationsByType(MyAutowired.class).length != 0) {
                    Class<?> fldClass = fld.getType();
                    if (!fldClass.isAnnotationPresent(MyBean.class)) throw new RuntimeException("Bean not configured");
                    Object fieldObject = objMap.getOrDefault(fldClass.getSimpleName(), fldClass.getDeclaredConstructor().newInstance());
                    objMap.put(fldClass.getSimpleName(), fieldObject);
                    fld.set(obj, fieldObject);
                }
            }
        }
    }

    public <T> T getBean(Class<T> clazz) {
        T obj = (T) objMap.get(clazz.getSimpleName());
        if (obj == null) throw new BeanNotFoundException("Bean Not found");
        return obj;
    }
}
