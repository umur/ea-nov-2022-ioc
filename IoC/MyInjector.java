package IoC;

import classFinder.ClassFinder;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyInjector {
    private Map<Class, Object> beans = new HashMap<>();

    private void scanAnnotations() {
        List<Class<?>> classes = ClassFinder.find("IoC");

        for (Class<?> cl : classes) {
            if (cl.isAnnotationPresent(MyBean.class)
                    || cl.isAnnotationPresent(MyAutowired.class)) {
                try {
                    beans.put(cl, cl.getConstructor().newInstance());
                } catch (NoSuchMethodException ignore) {
                } catch (InstantiationException ignore){
                } catch (IllegalAccessException ignore) {
                } catch (InvocationTargetException ignore){}
            }
        }
    }

    public Object getBean(Class clazz) {
        return null;
    }
}
