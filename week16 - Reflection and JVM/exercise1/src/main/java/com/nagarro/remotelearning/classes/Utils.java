package com.nagarro.remotelearning.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
	public static int getNumberOfLinesFromAFile(String path) {
		int numberOfLines = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			while (reader.readLine() != null) {
				numberOfLines++;
			}
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
		return numberOfLines;
	}
}
