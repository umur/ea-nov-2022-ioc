package com.example.ralphdeus;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyInjector {
    public final  Map<String,Object> Obj = new HashMap<String,Object>();

    public void AutoWiredObjects() {

        MyInjector m = new MyInjector();
        String packageName = m.getClass().getPackageName();
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        List<Class> classSet = reader.lines()
                .filter(l -> l.endsWith(".class"))
                .map(l -> getClass(l, packageName))
                .collect(Collectors.toList());

        try
        {
            for (Class item : classSet) {
                Class<?> classItem = ClassLoader.getSystemClassLoader().loadClass(item.getName());
                if(item.isAnnotationPresent(MyBean.class)!= true) {continue;}
                for(Field itemField: item.getDeclaredFields())
                {
                    if(itemField.isAnnotationPresent(MyAutoWired.class))
                    {

                    }
                }

            }
        }
        catch (Exception ex )
        {
            System.out.println(ex.getMessage());
        }

    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf(".")));
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
            return null;
        }
    }
    public Object getBean(Class c)
    {
        return Obj.get(c.getName());
    }
}