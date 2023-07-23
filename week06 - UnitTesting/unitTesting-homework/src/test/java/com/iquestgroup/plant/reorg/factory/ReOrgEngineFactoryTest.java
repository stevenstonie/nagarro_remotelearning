package com.iquestgroup.plant.reorg.factory;

import com.iquestgroup.plant.reorg.exception.UnqualifiedEmployeeException;
import com.iquestgroup.plant.reorg.domain.Employee;
import com.iquestgroup.plant.reorg.service.EmployeeService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReOrgEngineFactoryTest {
	@Mock
	private EmployeeService employeeService;
	@InjectMocks
	private EngineFactory engineFactory;

	@Test
	public void testIsNotAssemblyWorker() {
		Employee employee = new Employee("Jorge");
		EngineFactory engineFactory = new EngineFactory(employeeService);

		when(employeeService.isAssemblyLineWorker(employee)).thenReturn(false);

		assertThrows(UnqualifiedEmployeeException.class,
				() -> engineFactory.manufactureEngines(1, employee));
	}

	@Test
	public void testIsAssemblyWorker() {
		Employee employee = new Employee("Jotaro");
		EngineFactory engineFactory = new EngineFactory(employeeService);

		when(employeeService.isAssemblyLineWorker(employee)).thenReturn(true);

		engineFactory.manufactureEngines(1, employee);
	}
}