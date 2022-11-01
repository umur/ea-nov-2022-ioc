package edu.miu.ioc.core;

import edu.miu.ioc.core.annotation.MyAutowired;
import edu.miu.ioc.core.annotation.MyBean;
import edu.miu.ioc.core.exception.BeanNotFoundException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Author: Kuylim TITH
 * Date: 10/31/2022
 */
public class MyInjector {

    private static final Logger LOG = Logger.getLogger(MyInjector.class.getName());

    private MyInjector() {
    }

    public static void registerBeans(String packageName) {
        try {
            List<Class<?>> classes = ClassFinder.find(packageName);
            for (Class<?> cl : classes) {
                MyBean myBean = cl.getAnnotation(MyBean.class);
                if (!Objects.isNull(myBean)) {
                    InversionMap.BEANS.put(cl.getName(), Class.forName(cl.getName())
                            .getDeclaredConstructor().newInstance());
                }
            }
        } catch (Exception ex) {
            LOG.info("Failed to register bean in IOC: " + ex.getMessage());
        }
    }

    public static void validAndInjectBeans(String packageName) {
        List<Class<?>> classes = ClassFinder.find(packageName);
        for (Class<?> cl : classes) {
            MyBean myBean = cl.getAnnotation(MyBean.class);
            if (!Objects.isNull(myBean)) {
                Field[] fields = cl.getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(MyAutowired.class)) {
                        if (!InversionMap.BEANS.containsKey(field.getType().getName())) {
                            throw new BeanNotFoundException(String.format("No such bean: %s", field.getDeclaringClass().getName()));
                        } else {
                            field.trySetAccessible();
                            try {
                                Object injectObject = InversionMap.BEANS.get(field.getType().getName());
                                Object instanceObject = InversionMap.BEANS.get(cl.getName());
                                field.set(instanceObject, injectObject);
                            } catch (IllegalAccessException e) {
                                LOG.info("Failed to inject beans: " + e.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }


}
