package com.nagarro.remotelearning;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
	public static double getCompressionRatioOfTwoFiles(String inputFileName, String outputFileName) {
		long inputFileSize = 0;
		long outputFileSize = 0;
		try {
			inputFileSize = Files.size(Paths.get(inputFileName));
			outputFileSize = Files.size(Paths.get(outputFileName));
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
		return (double) outputFileSize / inputFileSize;
	}
}
