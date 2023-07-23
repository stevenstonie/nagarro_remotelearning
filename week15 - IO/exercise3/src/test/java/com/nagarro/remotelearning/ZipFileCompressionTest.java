package com.nagarro.remotelearning;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class ZipFileCompressionTest {
	private File tempFile;
	private String inputFileName = "input_file.txt";
	private String contents = "this just a sample text. nothing encoded here. nothing at all. i mean... almost nothing. (wink)";
	private String outputZipName = "output_zip_file.zip";
	private ZipFileCompression zipFileCompression;

	@BeforeEach
	public void setup(@TempDir Path tempDir) {
		zipFileCompression = new ZipFileCompression();
		tempFile = tempDir.resolve(inputFileName).toFile();

		try {
			Files.write(tempFile.toPath(), contents.getBytes());
		} catch (IOException e) {
			System.out.println("IOException occurred in main method, " + e.getMessage());
		}

		zipFileCompression.zipTheFile(tempFile.getAbsolutePath(), outputZipName);
	}

	@Test
	public void testIfZipIsCreatedSuccessfully() {
		File zipFile = new File(outputZipName);

		assertTrue(zipFile.exists() && !zipFile.isDirectory());
	}

	@Test
	public void testIfTheZipFileContentsAreTheSame() {
		String zipFileContents = zipFileCompression.readZipFile(outputZipName);

		assertEquals(contents, zipFileContents);
	}

	@Test
	public void testCompressionRatio() {
		double compressionRatio = Utils.getCompressionRatioOfTwoFiles(tempFile.getAbsolutePath(), outputZipName);
		System.out.println("the compression ratio is: " + compressionRatio);

		assertTrue(Math.abs(2.37 - compressionRatio) < 0.01);
	}
}
