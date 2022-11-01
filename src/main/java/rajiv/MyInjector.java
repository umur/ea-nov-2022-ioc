package rajiv;

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

    public MyInjector() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        init();
    }

    public void init() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setScanners(Scanners.TypesAnnotated);
        configurationBuilder.setUrls(ClasspathHelper.forPackage("rajiv"));

        Reflections reflections = new Reflections(configurationBuilder);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(MyBean.class);

        for(Class<?> c: classes){
            Object o = maps.getOrDefault(c.getSimpleName(), c.getDeclaredConstructor().newInstance());
            maps.put(c.getSimpleName(), o);

            Field[] fields = c.getDeclaredFields();

            for(Field f: fields){
                if(f.getDeclaredAnnotationsByType(MyAutowired.class).length != 0){
                    Class<?> fieldClass = f.getType();
                    if(!fieldClass.isAnnotationPresent(MyBean.class)) throw new RuntimeException("Bean not Configured");
                    Object fieldObject = maps.getOrDefault(fieldClass.getSimpleName(), fieldClass.getDeclaredConstructor().newInstance());
                    maps.put(fieldClass.getSimpleName(), fieldObject);
                    f.set(o, fieldObject);
                }
            }
        }
    }

    public <T> T getBean(Class<T> c) {
        T o = (T) maps.get(c.getSimpleName());
        if(o == null) throw new RuntimeException("Bean not Found!");
        return o;
    }
}