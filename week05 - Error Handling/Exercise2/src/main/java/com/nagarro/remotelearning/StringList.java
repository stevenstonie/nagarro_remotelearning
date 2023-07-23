package com.nagarro.remotelearning;

import com.iquestint.ju.CustomException;
import com.iquestint.ju.IList;
import java.util.ArrayList;
import java.util.List;

public class StringList implements IList<String> {
	private List<String> list;

	public StringList() {
		this.list = new ArrayList<String>(); // unchecked cast
	}

	public StringList(List<String> list) {
		this.list = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			this.list.add(list.get(i));
		}
	}

	public void add(String elem) {
		if (elem == null) {
			throw new CustomException("Null");
		}
		try {
			Integer.parseInt(elem);
		} catch (NumberFormatException e) {
			throw new CustomException("Invalid number.");
		}
		this.list.add(elem);
	}

	public String get(int pos) {
		try {
			return this.list.get(pos);
		} catch (IndexOutOfBoundsException e) {
			throw new CustomException("Index out of bounds.");
		}
	}

	public boolean contains(String elem) {
		return this.list.contains(elem);
	}

	public boolean containsAll(IList<String> foreignList) {
		for (int i = 0; i < foreignList.size(); i++) {
			if (!this.list.contains(foreignList.get(i))) {
				return false;
			}
		}
		return true;
	}

	public int size() {
		return this.list.size();
	}
}
