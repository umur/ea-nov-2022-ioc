package com.example.Lab1.Inversion.of.Control;



import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;




@MyBean
public class MyInjector {

    private Map<Class,Object> iocContainer = new HashMap<>();
    public Map<Class, Object> getiocContainer() {
        return iocContainer;
    }

    public void setiocContainer(Map<Class, Object> iocContainer) {
        this.iocContainer = iocContainer;
    }
    public  Set<Class> findAllClassesWithMyBeanAnnotation(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false),new TypeAnnotationsScanner());
        return reflections.getTypesAnnotatedWith(MyBean.class)
                .stream()
                .collect(Collectors.toSet());
    }
    public void setFieldDependency() throws InstantiationException, IllegalAccessException {
        Set<Class> cls = findAllClassesWithMyBeanAnnotation("com.example.demoapplications");
        for(Class cl : cls){
                iocContainer.put(cl,cl.newInstance());
                Arrays.stream(cl.getDeclaredFields()).filter(field1 -> field1.getAnnotation(MyAutowired.class)!=null).collect(Collectors.toSet()).forEach(
                field1 -> {
                    try {
                        field1.setAccessible(true);
                        field1.set(iocContainer.get(field1.getDeclaringClass()),iocContainer.get(field1.getType()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
        }
    }

    public Object getBean(Class clazz) throws BeanNotFoundException {
        if(iocContainer.containsKey(clazz))
            return iocContainer.get(clazz);
        throw new BeanNotFoundException("Bean not Found!!");
    }
}
