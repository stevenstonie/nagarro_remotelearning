package com.nagarro.remotelearning.classes;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
	private Object targetClass;

	public MyInvocationHandler(Object targetClass) {
		this.targetClass = targetClass;
	}

	@Override
	public Object invoke(Object proxy, Method targetMethod, Object[] args) {
		if (isAnnotationPresent(targetClass, targetMethod)) {
			logClassInfo(targetMethod, args);
		}

		return invokeMethod(targetMethod, args);
	}

	private Object invokeMethod(Method targetMethod, Object[] args) {
		try {
			return targetMethod.invoke(targetClass, args);
		} catch (IllegalAccessException | InvocationTargetException e) {
			System.out.println("Exception occured while invoking method: " + e.getMessage());
		}
		return null;
	}

	private boolean isAnnotationPresent(Object targetClass, Method targetMethod) {
		return targetMethod.isAnnotationPresent(LoggerAnnotation.class)
				|| targetClass.getClass().isAnnotationPresent(LoggerAnnotation.class);
	}

	private void logClassInfo(Method targetMethod, Object[] args) {
		Logger.log("Object type: " + targetClass.getClass().getName());
		Logger.log("Method name: " + targetMethod.getName());
		Logger.log("Method arguments: " + args[0] + ", " + args[1]);
		try {
			Logger.log("Returned value: " + targetMethod.invoke(targetClass, args));
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
