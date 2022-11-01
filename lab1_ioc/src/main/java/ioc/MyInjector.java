package ioc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class MyInjector {
    Map<Class, Object> beanMap = new HashMap<>();
    MyInjector(){
        this.beanMap = getClasses();
    }

    public static void main(String[] args){
        MyInjector blackBox = new MyInjector();
        try{
            classA classABean = blackBox.getBean(classA.class);
            classABean.printClass();
        }catch(BeanNotFoundException e){
            System.out.println("Bean not found");
        }
    }

    public <T> T getBean(Class<T> clss) throws BeanNotFoundException {
        T bean = (T) this.beanMap.get(clss);
        if(bean == null){
            throw new BeanNotFoundException("Class instance not found", new Error());
        }
        return bean;
    }

    private Map<Class, Object> getClasses(){
        String packageName = "ioc";
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Map<Class, Object> beanMap = new HashMap<>();
        reader.lines()
                .map(line -> getClass(line, packageName))
                .filter(clss -> {
                    for(Field field: clss.getDeclaredFields()){
                        if(field.isAnnotationPresent(MyAutowired.class)){
                            try {
                                beanMap.put(clss, clss.getDeclaredConstructor().newInstance());
                            } catch (InstantiationException e) {
                                throw new RuntimeException(e);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            } catch (InvocationTargetException e) {
                                throw new RuntimeException(e);
                            } catch (NoSuchMethodException e) {
                                throw new RuntimeException(e);
                            }
                            return true;
                        }
                    }

                    return false;
                }).count();
        return beanMap;
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found: " + e);
        }
        return null;
    }
}
