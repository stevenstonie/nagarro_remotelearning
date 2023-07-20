package com.nagarro.remotelearning.classes;

@FunctionalInterface
public interface Combiner<T> {
	T combine(T a, T b, T c, T d);
}