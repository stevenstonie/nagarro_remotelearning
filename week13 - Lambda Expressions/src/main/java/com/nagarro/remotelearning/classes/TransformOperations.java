package com.nagarro.remotelearning.classes;

import java.util.List;
import java.util.function.Consumer;

public class TransformOperations {
	public static <T> void applyTransformations(T[] data, Combiner<T> combiner, Consumer<T> consumer) {
		if (data.length < 4) {
			throw new IllegalArgumentException("array length must be greater than or equal to 4");
		}

		consumer.accept(combiner.combine(data[0], data[1], data[2], data[3]));
	}
}
