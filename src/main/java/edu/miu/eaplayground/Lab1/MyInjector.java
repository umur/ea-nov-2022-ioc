package edu.miu.eaplayground.Lab1;

import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MyInjector {
    Map<Class<?>,Object> map = new HashMap<>();

    public MyInjector(){
        Reflections reflections = new Reflections("edu.miu.eaplayground");

        reflections.getTypesAnnotatedWith(MyBean.class).forEach(cls->{
            try{
                Object object = cls.newInstance();
                Arrays.stream(cls.getFields()).filter(f -> f.isAnnotationPresent(MyAutowired.class))
                        .toList()
                        .forEach(field -> {
                            Class<?> innerClass = field.getType();
                            try{
                                Object innerInstance = innerClass.newInstance();
                                map.put(innerClass, innerInstance);
                                field.set(object,innerInstance);
                            }
                            catch (InstantiationException | IllegalAccessException ex){
                                throw  new RuntimeException(ex);
                            }
                        });
                    map.put(cls,object);
            }catch (InstantiationException | IllegalAccessException ex){
                throw new RuntimeException(ex);
            }

        });
    }

    public Object getBean(Class<?> clazz) throws NotFoundBeanException{
        try{
            return map.get(clazz);
        }catch(Exception ex){
            throw new NotFoundBeanException("Error");
        }
    }
}
