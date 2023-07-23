package com.nagarro.remotelearning.exercise1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyCollectionImpl<T> implements MyCollection<T> {
	private List<T> list;

	public MyCollectionImpl() {
		this.list = new ArrayList<>();
	}

	public boolean containsAll(Collection<T> c) {
		return list.containsAll(c);
	}

	public boolean addAll(Collection<T> c) {
		return list.addAll(c);
	}
}
