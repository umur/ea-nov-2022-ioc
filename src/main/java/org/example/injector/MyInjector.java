package org.example.injector;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;
import org.example.annotations.MyAutowired;
import org.example.annotations.MyBean;
import org.example.exception.BeanNotFoundException;

import java.lang.reflect.*;


public class MyInjector {
    private static Map<Class<?>, Object> beanMap = new HashMap<>();
    private static MyInjector myInjector;

    private MyInjector(){}

    public static void start(Class<?> mainClass){
        try{
            synchronized (MyInjector.class){
                if(myInjector == null) {
                    myInjector = new MyInjector();
                    myInjector.init(mainClass);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public static Object getBean(Class<?> clazz) throws Exception {
        if(!beanMap.containsKey(clazz)) {
            throw new BeanNotFoundException("Cannot find bean " + clazz);
        }
        return beanMap.get(clazz);
    }

    public void init(Class<?> mainClass) throws IOException, ClassNotFoundException {
        Class<?>[] classes = this.getClasses(mainClass.getPackage().getName());
        for (Class<?> cl : classes) {
            if (cl.isAnnotationPresent(MyBean.class)) {
                try {
                    this.beanMap.putIfAbsent(cl, cl.getConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                Field[] fields = cl.getDeclaredFields();
                for(Field field : fields) {
                    if(field.getAnnotation(MyAutowired.class) != null) {
                        field.setAccessible(true);
                        Class<?> type = field.getType();
                        try {
                            this.beanMap.putIfAbsent(type, type.getConstructor().newInstance());
                            Object currentObj= this.beanMap.get(cl);
                            Object newValue = this.beanMap.get(type);
                            field.set(currentObj, newValue);
                        } catch(Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }
        }
    }

    public Class<?>[] getClasses(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        List<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(this.findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    public List<Class<?>> findClasses(File dir, String packageName) throws ClassNotFoundException {
        List<Class<?>> classList = new ArrayList<>();
        if(!dir.exists()) return classList;
        File[] files = dir.listFiles();
        for(File f : files){
            if(f.isDirectory()) {
                if (!f.getName().contains(".")) {
                    classList.addAll(findClasses(f, packageName+ "." + f.getName()));
                }
            }else if(f.getName().contains(".class")){
                String className = packageName + "." + f.getName().substring(0, f.getName().length() - ".class".length());
                classList.add(Class.forName(className));
            }
        }
        return classList;
    }
}
