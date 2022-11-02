package classes;

import annotation.MyAutowired;
import annotation.MyBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyInjector {

    private  final Map<String, Object>  classCollectionMap = new HashMap<>();

    public void scanAnnotations(){
        MyInjector myInjector = new MyInjector();
        String currentPackage = myInjector.getClass().getPackageName();
        List<Class> classesList = myInjector.getAllClassesByPackageName(currentPackage);
        try {
            for (Class item : classesList) {
                Class<?> classItem = ClassLoader.getSystemClassLoader().loadClass(item.getName());
                if(item.isAnnotationPresent(MyBean.class)!= true)
                {
                    continue;
                }
                for(Field itemField: item.getDeclaredFields())
                {
                    if(itemField.isAnnotationPresent(MyAutowired.class))
                    {
                        classCollectionMap.put(classItem.getSimpleName(), itemField.getDeclaringClass().newInstance());
                    }
                }
            }
        } catch (ClassNotFoundException |  IllegalAccessException | InstantiationException e){
            System.out.println(e.getMessage());
        }
    }

    private List<Class> getAllClassesByPackageName(String packageName){
     InputStream stream = ClassLoader.getSystemClassLoader()
             .getResourceAsStream(packageName.replaceAll("[.]", "/"));
      BufferedReader reader =  new BufferedReader(new InputStreamReader(stream));
      List<Class> classList = reader.lines().filter(line -> line.endsWith(".class"))
              .map(line -> getClass(line, packageName))
              .collect(Collectors.toList());
      return classList;
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
           e.printStackTrace();
           return null;
        }
    }

    public Object getBean(Class c) throws  BeanNotFoundException
    {
        if(classCollectionMap.get(c.getName())!=null)
        {
            return classCollectionMap.get(c.getName());
        }
        else {
            throw new BeanNotFoundException();
        }
    }
}
