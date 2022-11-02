package IoC;

import classFinder.ClassFinder;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyInjector {
    private Map<Class, Object> beans = new HashMap<>();
    private String pack;

    public MyInjector(String pack) {
        this.pack = pack;
        scanAnnotations();
    }

    private void scanAnnotations() {
        List<Class<?>> classes = ClassFinder.find(pack);

        for (Class<?> cl : classes) {
            if (cl.isAnnotationPresent(MyBean.class)) {
                try {
                    beans.put(cl, cl.getConstructor().newInstance());
                } catch (NoSuchMethodException ignore) {
                } catch (InstantiationException ignore){
                } catch (IllegalAccessException ignore) {
                } catch (InvocationTargetException ignore){}
            }
        }
    }

    public <T> T getBean(Class<T> clazz) {
        return clazz.cast(beans.get(clazz));
    }
}
