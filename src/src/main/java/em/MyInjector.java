package em;

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
        BeanInjector();
    }

    public void BeanInjector() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setScanners(Scanners.TypesAnnotated);
        configurationBuilder.setUrls(ClasspathHelper.forPackage("em"));

        Reflections reflections = new Reflections(configurationBuilder);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(MyBean.class);

        for (Class<?> classList : classes) {
            System.out.println(classList.getSimpleName());
            Object obj = maps.getOrDefault(classList.getSimpleName(), classList.getDeclaredConstructor().newInstance());
            System.out.println(obj);
            maps.put(classList.getSimpleName(), obj);
            Field[] fields = classList.getDeclaredFields();
            for (Field f : fields) {
                if (f.getDeclaredAnnotationsByType(MyAutowired.class).length != 0) {
                    Class<?> type = f.getType();
                    if (!type.isAnnotationPresent(MyBean.class)) throw new RuntimeException("Bean is not configured");
                    Object fType = maps.getOrDefault(type.getSimpleName(), type.getDeclaredConstructor().newInstance());
                    maps.put(type.getSimpleName(), fType);
                    f.set(obj, fType);
                }
            }
        }
    }

    //Generic bean instance finder
    public <T> T getBean(Class<T> c) {
        T type = (T) maps.get(c.getSimpleName());
        if (type == null) throw new RuntimeException("Bean not found in the package");
        return type;
    }
}
