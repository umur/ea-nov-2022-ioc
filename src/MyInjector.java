package src;

import src.annotations.MyAutowired;
import src.exceptions.BeanNotFoundException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class MyInjector {
    public Map<Class,Object>  iocContainer=new HashMap<>();

    //method to find all classes
    public Set<Object> scanAllClassesWithPackageName(String nameOfPackage){
        InputStream stream=ClassLoader.getSystemClassLoader().
                getResourceAsStream(nameOfPackage.replaceAll("[.]","/"));

        BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
        System.out.println(reader);

    return reader.lines().filter( line-> line.endsWith(".class")).map(line -> getClass(line,nameOfPackage)).
            collect(Collectors.toSet());
    }

    private Object getClass(String line,String nameOfPackage){
        try {
            return Class.forName(nameOfPackage + "." + line.substring(0,line.lastIndexOf('.')));
        }catch (ClassNotFoundException e){

        }
        return null;
    }

    private boolean hasAnnotation(Class clx){
        List<Field> privateFields=new ArrayList<>();
        Field[] allField = clx.getDeclaredFields();
        for( Field f : allField){
            if(Modifier.isPrivate(f.getModifiers())){

                privateFields.add(f);
            }
        }

        for(Field f: privateFields){

            if(f.isAnnotationPresent(MyAutowired.class)){
                return true;
            }
        }
        return false;
    }

    public void searchBean() throws Exception {
        Set<Object> classes = scanAllClassesWithPackageName("src");
        for (Object cls : classes) {
            String thisClass = cls.toString().split(" ")[1];
            if (hasAnnotation(Class.forName(thisClass))) {
                iocContainer.put(Class
                                .forName(thisClass)
                        , Class.forName(thisClass)
                                .getConstructor()
                                .newInstance());
            }
        }

    }

    public Object getBean(Class cls) throws BeanNotFoundException {
        try {
            return iocContainer.get(cls);
        } catch (Exception ex) {
            throw new BeanNotFoundException("Bean Was Not Found!");
        }
    }

}
