import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class MyInjector {

    private static Map<String, Object> instances = new HashMap<>();

    // scan all classes under the current package
    public static void scan() throws Exception {
        // get the current package
        Package pkg = MyInjector.class.getPackage();
        // get the current package name
        String pkgName = pkg.getName();
        // get the current class loader
        ClassLoader classLoader = MyInjector.class.getClassLoader();
       // get file name of the current package
        String path = pkgName.replace('.', '/');
        // get the URL of the current package
       URL resource = classLoader.getResource(path);
        // get the current package as a file
        File file = new File(resource.getFile());
        // get all files in the current package
        File[] files = file.listFiles();
        // loop through all files in the current package
        for (File f : files) {
            // get the file name
            String fileName = f.getName();
            //System.out.println("File name: " + fileName);
            // if the file name ends with .class
            if (fileName.endsWith(".class")) {
                // get the class name
                String className = fileName.substring(0, fileName.length() - 6);
                // get the class
                //System.out.println("Class name: " + className);
               // System.out.println("Package name: " + pkgName);
                Class<?> cls;

                if (pkgName.equals("")) {
                    cls = Class.forName(className);
                } else {
                    cls = Class.forName(pkgName + "." + className);
                }

                // get all annotations of the class
                Annotation annotations = cls.getAnnotation(MyBean.class);
                if(annotations != null) {

                    instances.putIfAbsent(cls.getName(), cls.getDeclaredConstructor().newInstance());
                    
                }
                   
            }
        }
    }

   // get instance from the IoC container
    public static Object getBean(Class clazz) throws BeanNotFoundException {
        // get the class name
        String className = clazz.getName();
        // get the instance of the class
        Object instance = instances.get(className);
        // if the instance is null
        System.out.println("Instance: " + instance);
        if (instance == null) {
            // throw a BeanNotFoundException
            throw new BeanNotFoundException("Bean not found: " + className);
        }
        // return the instance
        return instance;
    }

    

}