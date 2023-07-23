package com.nagarro.remotelearning.classes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class CleanCodeChecker {
	List<Class<?>> alreadyCheckedClasses;

	public CleanCodeChecker() {
		alreadyCheckedClasses = new ArrayList<>();
	}

	public void checkCleanCodeForClass(Class<?> _class) {
		if (alreadyCheckedClasses.contains(_class)) {
			return;
		}
		alreadyCheckedClasses.add(_class);

		System.out.println("~checking the class for clean code: " + _class.getSimpleName());
		checkFieldsOfClass(_class);
		checkMethodsOfClass(_class);

		if (_class.getSuperclass() != null) {
			checkCleanCodeForClass(_class.getSuperclass());
		}
	}

	public boolean checkFieldsOfClass(Class<?> _class) {
		Field[] fields = _class.getDeclaredFields();
		boolean isCleanCode = true;

		for (Field field : fields) {
			if (!Modifier.isPrivate(field.getModifiers())) {
				System.out.println("   field " + field.getName() + " should be private");
				isCleanCode = false;
			}

			if (!Character.isLowerCase(field.getName().charAt(0))) {
				System.out.println("   field " + field.getName() + " should start with a lowercase letter");
				isCleanCode = false;
			}
		}

		return isCleanCode;
	}

	public boolean checkMethodsOfClass(Class<?> _class) {
		Method[] methods = _class.getDeclaredMethods();
		boolean isCleanCode = true;

		for (Method method : methods) {
			if (!Character.isLowerCase(method.getName().charAt(0))) {
				System.out.println("   method " + method.getName() + " should start with a lowercase letter");
				isCleanCode = false;
			}
		}

		return isCleanCode;
	}
}
