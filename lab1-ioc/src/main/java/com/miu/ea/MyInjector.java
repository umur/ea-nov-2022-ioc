package com.miu.ea;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("rawtypes")
public class MyInjector {
    private HashMap<Class, Object> mapForObjects = new HashMap<>();
    private final String[] myAnnotations = {"MyBean"};
    public void createInstances(String packageName) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Class> listOfClasses = findAllClassesUsingClassLoader(packageName);

        for (Class curClass : listOfClasses) {
//            System.out.println(curClass);
            for (Annotation annotation : Arrays.stream(curClass.getAnnotations()).toList()) {
                String[] splitAnnotation = annotation.toString().split("\\.");
                if (splitAnnotation.length == 0) continue;

                String annotationName = splitAnnotation[splitAnnotation.length - 1];
                annotationName = annotationName.replace("()", "");

                if (Arrays.asList(myAnnotations).contains(annotationName)){
                    //TODO add to mapping if found annotation
                    if (!mapForObjects.containsKey(curClass)){
                        Object newObject = curClass.getConstructor().newInstance();
                        mapForObjects.put(curClass, newObject);
                        System.out.printf("Object %s is created for the class %s %n", newObject, curClass);
                    }
                }
            }
        }
    }

    private List<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader;
        if (stream == null) {
            return null;
        }
        reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toList());

    }
    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

    public Object getBean(Class clazz){
        return this.mapForObjects.get(clazz);
    }
}
