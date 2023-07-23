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

public class DeflationCompressionTest {
	private File tempFile;
	private String inputFileName = "input_file.txt";
	private String contents = "this just a sample text. nothing encoded here. nothing at all. i mean... almost nothing. (wink)";
	private String outputDeflatedFileName = "output_file.txt";
	private DeflationCompression deflationCompression;

	@BeforeEach
	public void setup(@TempDir Path tempDir) {
		deflationCompression = new DeflationCompression();
		tempFile = tempDir.resolve(inputFileName).toFile();

		try {
			Files.write(tempFile.toPath(), contents.getBytes());
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}

		deflationCompression.deflateTheFile(tempFile.getAbsolutePath(), outputDeflatedFileName);
	}

	@Test
	public void testIfOutputFileIsCreatedSuccessfully() {
		File zipFile = new File(outputDeflatedFileName);

		assertTrue(zipFile.exists() && !zipFile.isDirectory());
	}

	@Test
	public void testIfTheOutputFileContentsAreTheSame() {
		String deflatedFileContents = deflationCompression.readDeflatedFile(outputDeflatedFileName);

		assertEquals(contents, deflatedFileContents);
	}

	@Test
	public void testCompressionRatio() {
		double compressionRatio = Utils.getCompressionRatioOfTwoFiles(tempFile.getAbsolutePath(),
				outputDeflatedFileName);
		System.out.println("the compression ratio is: " + compressionRatio);

		assertTrue(Math.abs(0.84 - compressionRatio) < 0.01);
	}
}
