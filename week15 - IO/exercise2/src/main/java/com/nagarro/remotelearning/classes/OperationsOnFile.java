package com.nagarro.remotelearning.classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class OperationsOnFile {
	public List<Integer> readThirdAndFourthIntegerFromFile(File inputFile) {
		try (RandomAccessFile reader = new RandomAccessFile(inputFile, "r")) {
			String line = reader.readLine();

			if (line != null) {
				String[] integers = line.split(" ");

				int thirdInteger = Integer.parseInt(integers[2]);
				int fourthInteger = Integer.parseInt(integers[3]);

				return List.of(thirdInteger, fourthInteger);
			}

		} catch (IOException | NumberFormatException e) {
			System.out.println("exception occured in main method, " + e.getMessage());
		}

		return null;
	}

	public void print4IntegersIntoFile(File inputFile) {
		try (FileWriter fileWriter = new FileWriter(inputFile);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);) {
			int a = 1;
			int b = 2;
			int c = 3;
			int d = 4;

			bufferedWriter.write(a + " ");
			bufferedWriter.write(b + " ");
			bufferedWriter.write(c + " ");
			bufferedWriter.write(d + " ");
		} catch (IOException e) {
			System.out.println("IOException occured in main method, " + e.getMessage());
		}
	}
}
