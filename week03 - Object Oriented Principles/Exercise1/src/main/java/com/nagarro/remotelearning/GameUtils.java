package main.java.com.nagarro.remotelearning;

import java.util.List;

import classes.Entity;

public class GameUtils {
	public static float log2(float x) {
		return (float) (Math.log(x) / Math.log(2) + 1e-10);
	}

	public static List<Entity> makeArrayList(Entity... entities) {
		return List.of(entities);
	}
}
