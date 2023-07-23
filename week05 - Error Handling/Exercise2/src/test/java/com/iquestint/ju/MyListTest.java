package com.iquestint.ju;

import com.nagarro.remotelearning.StringList;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MyListTest {

	private StringList list;

	private Class<CustomException> exceptionType = CustomException.class;

	private String[] initData = { "12", "23", "34", "45" };

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() {
		this.list = new StringList();
	}

	@Test
	public void testAddValuesToTheList() {
		initData();
		assertEquals(initData.length, list.size());
		for (String data : initData) {
			assertTrue(list.contains(data));
		}
	}

	@Test
	public void testAddNonIntegerValue() {
		exception.expect(exceptionType);
		exception.expectMessage("Invalid number.");
		list.add("Hey, I'm not an integer.");
	}

	@Test
	public void testAddNonNullValue() {
		exception.expect(exceptionType);
		exception.expectMessage("Null");
		list.add(null);
	}

	@Test
	public void testIndexOutOfBounds() {
		initData();
		exception.expect(exceptionType);
		exception.expectMessage("Index out of bounds.");
		list.get(initData.length);
	}

	private void initData() {
		for (String numberAsString : initData) {
			list.add(numberAsString);
		}
	}
}
