package com.nagarro.remotelearning;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.nagarro.remotelearning.classes.MapLinesWithPeople;
import com.nagarro.remotelearning.classes.Utils;
import com.nagarro.remotelearning.week1ex1.Person;

public class Week1Ex1ReflectionTest {
	MapLinesWithPeople mapLinesWithPeople;
	String pathOfPeopleInput = "src\\main\\java\\com\\nagarro\\remotelearning\\week1ex1\\people_input.txt";

	@BeforeEach
	public void setUp() {
		mapLinesWithPeople = new MapLinesWithPeople(pathOfPeopleInput);
	}

	@Test
	public void testIfNbOfPeopleIsTheSameAsNbOfLines() {
		mapLinesWithPeople.insertPeopleIntoList();

		assertTrue(mapLinesWithPeople.getPeople().size() == Utils.getNumberOfLinesFromAFile(pathOfPeopleInput));
	}

	@Test
	public void testIfADeceasedPersonIsSuccesfullyCreated() {
		Person person = mapLinesWithPeople.createPerson("James", "Hurricane", "1987", "1988");

		assertTrue(person.getFirstName().equals("James"));
		assertTrue(person.getLastName().equals("Hurricane"));
		assertTrue(person.getBirthYear().equals("1987"));
		assertTrue(person.getDeceaseYear().equals("1988"));
	}

	@Test
	public void testIfALivingPersonIsSuccesfullyCreated() {
		Person person = mapLinesWithPeople.createPerson("James", "Hurricane", "b.1987");

		assertTrue(person.getFirstName().equals("James"));
		assertTrue(person.getLastName().equals("Hurricane"));
		assertTrue(person.getBirthYear().equals("1987"));
		assertNull(person.getDeceaseYear());
	}

	@Test
	public void testMappingEachLineToAPersonAccordingly(@TempDir Path tempDir) {
		File tempFile = createTempFileWithCustomInput(tempDir);
		MapLinesWithPeople mapLinesWithPeople = new MapLinesWithPeople(tempFile.getAbsolutePath());

		mapLinesWithPeople.insertPeopleIntoList();

		assertTrue(mapLinesWithPeople.getPeople().size() == 3);
		assertTrue(mapLinesWithPeople.getPeople().get(0).getLastName().equals("Johnson"));
		assertTrue(mapLinesWithPeople.getPeople().get(1).getBirthYear().equals("1987"));
		assertTrue(mapLinesWithPeople.getPeople().get(2).getDeceaseYear() == null);
	}

	private File createTempFileWithCustomInput(Path tempDir) {
		File tempFile = tempDir.resolve("temp_file.txt").toFile();
		String people = "Daniel, Johnson, b.1929\nLucas, Dimitrov, 1987, 1988\nAndrei, Cazacu, b.2001\n";

		try {
			Files.write(tempFile.toPath(), people.getBytes());
		} catch (IOException e) {
			System.out.println("IOException occured in main method, " + e.getMessage());
		}

		return tempFile;
	}
}
