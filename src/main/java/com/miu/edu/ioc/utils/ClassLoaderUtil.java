package com.miu.edu.ioc.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassLoaderUtil {
	private static final char DOT = '.';
	private static final char SLASH = '/';
	private static final String CLASS_SUFFIX = ".class";

	/**
	 * Get all the classes in the input package name
	 *
	 * @param packageName
	 * @return Array of classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Class<?>[] getClasses(String packageName) throws ClassNotFoundException, IOException {
		// Convert package name to path
		String path = packageName.replace(DOT, SLASH);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> directories = new ArrayList<>();

		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			directories.add(new File(resource.getFile()));
		}
		List<Class<?>> classes = new ArrayList<>();
		for (File directory : directories) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	/**
	 * Get all classes for the input package name and inside the input directory
	 *
	 * @param directory
	 * @param packageName
	 * @return list of classes
	 * @throws ClassNotFoundException
	 */
	public static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				classes.addAll(findClasses(file, packageName + DOT + file.getName()));
			} else if (file.getName().endsWith(CLASS_SUFFIX)) {
				String className = packageName + DOT + file.getName().substring(0, file.getName().length() - CLASS_SUFFIX.length());
				classes.add(Class.forName(className));
			}
		}
		return classes;
	}

}
