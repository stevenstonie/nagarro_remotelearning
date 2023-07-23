package com.iquestint.ju;

public interface IList<T> {
	void add(T element);

	T get(int positon);

	boolean contains(T element);

	boolean containsAll(IList<T> foreignList);

	int size();
}
