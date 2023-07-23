package com.nagarro.remotelearning;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nagarro.remotelearning.classes.Aircraft;
import com.nagarro.remotelearning.classes.Car;
import com.nagarro.remotelearning.classes.CleanCodeChecker;
import com.nagarro.remotelearning.classes.Vehicle;

public class CleanCodeCheckerTest {
	CleanCodeChecker cleanCodeChecker = new CleanCodeChecker();
	private final ByteArrayOutputStream tempOut = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@BeforeEach
	public void setUpStream() {
		System.setOut(new PrintStream(tempOut));
	}

	@AfterEach
	public void restoreStream() {
		tempOut.reset();
		System.setOut(originalOut);
	}

	@Test
	public void testVehicleSpeedField() {
		cleanCodeChecker.checkCleanCodeForClass(Vehicle.class);

		assertTrue(tempOut.toString().contains("Speed"));
	}

	@Test
	public void testAircraftNumberOfPropellersField() {
		cleanCodeChecker.checkCleanCodeForClass(Aircraft.class);

		assertTrue(tempOut.toString().contains("numberOfPropellers"));
	}

	@Test
	public void testCarFields() {
		assertTrue(cleanCodeChecker.checkFieldsOfClass(Car.class));
	}

	@Test
	public void testAllParentClasses() {
		cleanCodeChecker.checkCleanCodeForClass(Car.class);

		assertTrue(tempOut.toString().contains("Car"));
		assertTrue(tempOut.toString().contains("Vehicle"));
		assertTrue(tempOut.toString().contains("Object"));
	}
}
