import org.reflections.Reflections;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.*;
import java.util.Objects;


public class MyInjector {

    Map<Class<?>, Object> map = new HashMap<>();
    public MyInjector(){
        Reflections reflections = new Reflections("java");
        reflections.getTypesAnnotatedWith(MyBean.class).forEach(aClass ->{
            try{
               Object t = aClass.getDeclaredConstructor().newInstance();
               Arrays.stream(aClass.getFields()).filter(f -> f.isAnnotationPresent(MyAutowired.class))
                       .toList()
                       .forEach(field ->{
                           Class<?> innerClass = field.getType();
                       });
               map.put(aClass, t);
            } catch (IllegalAccessException e){
                throw new RuntimeException();
            } catch (InvocationTargetException | InstantiationException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        } );
//
    }

    public Object getBean(Class<?> clazz) throws ClassNotFoundException, NotFoundBeanException{
        try{
            return map.get(clazz);
        } catch (Exception e){
            throw new NotFoundBeanException("Error");
        }
    }

}
