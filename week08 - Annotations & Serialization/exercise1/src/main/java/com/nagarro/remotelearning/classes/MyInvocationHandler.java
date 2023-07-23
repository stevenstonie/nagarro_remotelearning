package com.nagarro.remotelearning.classes;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
	private Object target;

	public MyInvocationHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) {
		try {
			Logger.log("Object type: " + target.getClass().getName());
			Logger.log("Method name: " + method.getName());
			Logger.log("Method arguments: " + args[0] + ", " + args[1]);
			Logger.log("Returned value: " + method.invoke(target, args));

			return method.invoke(target, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}
}
