package com.example.miu.edu.ioc;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ClassChecker {
    private static final char DOT = '.';
    private static final char SLASH = '/';
    private static final String CLASS_SUFFIX = ".class";
    private static final String BAD_PACKAGE_ERROR
            = "Unable to get resources from path '%s'. Are you sure the package '%s' exists?";

    public static void main(String[] args) {
        var classes = find("com.example.miu.edu.ioc");
        classes.forEach(cl -> System.out.println(cl.getName()));
    }

    public static <T> List <Class<T>> find(String scannedPackage) {
        String scannedPath = scannedPackage.replace(DOT, SLASH);
        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
        if (scannedUrl == null) {
            throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedPath, scannedPackage));
        }
        File scannedDir = new File (scannedUrl.getFile());
        var classes = new ArrayList <Class<T>> ();
        for (File file : scannedDir.listFiles()) {
            classes.addAll(find(file, scannedPackage));
        }
        return classes;
    }

    private  static <T> List<Class<T>> find(File file, String scannedPackage) {
        var classes = new ArrayList<Class<T>>();
        String resource = scannedPackage + DOT + file.getName();
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                classes.addAll(find(child, resource));
            }
        } else if (resource.endsWith(CLASS_SUFFIX)) {
            int endIndex = resource.length() - CLASS_SUFFIX.length();
            String className = resource.substring(0, endIndex);
            try {
                classes.add((Class<T>) Class.forName(className));
            } catch (ClassNotFoundException ignore) {
            }
        }
        return classes;
    }
}
