package com.nagarro.remotelearning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import com.nagarro.remotelearning.classes.OperationsOnFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class OperationsOnFileTest {
	private OperationsOnFile operationsOnFile;
	File tempFile;

	@BeforeEach
	public void setup(@TempDir Path tempDir) {
		operationsOnFile = new OperationsOnFile();
		tempFile = tempDir.resolve("temp_file.txt").toFile();

		try {
			Files.write(tempFile.toPath(), "4 3 2 1".getBytes());
		} catch (IOException e) {
			System.out.println("IOException occured in main method, " + e.getMessage());
		}
	}

	@Test
	public void testReadThirdAndFourthIntegerFromTempFile() {
		assertEquals(List.of(2, 1), operationsOnFile.readThirdAndFourthIntegerFromFile(tempFile));
	}

	@Test
	public void testPrint4IntegersIntoFile() {
		operationsOnFile.print4IntegersIntoFile(tempFile);

		assertEquals(List.of(3, 4), operationsOnFile.readThirdAndFourthIntegerFromFile(tempFile));
	}
}
