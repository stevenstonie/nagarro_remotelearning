package com.iquestgroup.plant.initial.factory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.iquestgroup.plant.initial.domain.*;
import com.iquestgroup.plant.initial.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InitialEngineFactoryTest {
	private EngineFactory engineFactory;
	private List<Employee> employees;
	private List<EngineComponent> engineComponents;

	@BeforeEach
	public void setup() {
		employees = new ArrayList<Employee>();
		employees.add(new Employee("Jojo", true, true));
		employees.add(new Employee("Jane", false, true));
		employees.add(new Employee("Joe", true, false));

		EngineComponent component1 = new EngineComponent("Component1", 1);
		EngineComponent component2 = new EngineComponent("Component2", 2);
		EngineComponent component3 = new EngineComponent("Component3", 3);
		EngineComponent component4 = new EngineComponent("Component4", 4);
		EngineComponent component5 = new EngineComponent("Component5", 5);
		EngineComponent component6 = new EngineComponent("Component6", 6);
		EngineComponent component7 = new EngineComponent("Component7", 7);
		engineComponents = new ArrayList<EngineComponent>(
				Arrays.asList(component1, component2, component3, component4, component5, component6, component7));

		engineFactory = new EngineFactory(employees, engineComponents);
	}

	@Test
	public void testUnauthorizedEmployeeException() {
		// not an employee of the factory
		assertThrows(UnauthorizedEmployeeException.class,
				() -> {
					engineFactory.manufactureEngines(1, new Employee("Johan", false, true));
				});
	}

	@Test
	public void testUnqualifiedEmployeeException() {
		// not a line worker
		assertThrows(
				UnqualifiedEmployeeException.class,
				() -> {
					engineFactory.manufactureEngines(1, employees.get(2));
				});
	}

	@Test
	public void testNotEnoughComponentsInStock() {
		assertThrows(
				InsufficientStockException.class,
				() -> {
					engineFactory.manufactureEngines(3, employees.get(0));
				});
	}

	@Test
	public void testManufactureEngines() {
		ArrayList<Engine> expectedEngines = new ArrayList<Engine>(
				engineFactory.manufactureEngines(2, employees.get(0)));

		assertEquals(2, expectedEngines.size());
	}
}
