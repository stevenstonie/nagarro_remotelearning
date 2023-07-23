package com.nagarro.remotelearning.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class TranscoderFromLatin1 {

	public void transcodeFromLatin1(String inputFileName, String outputFileName) {
		try (BufferedReader reader = createBufferedReader(inputFileName);
				BufferedWriter writer = createBufferedWriter(outputFileName);) {

			String line;
			while ((line = reader.readLine()) != null) {
				writer.write(line);
				writer.newLine();
			}
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
	}

	private BufferedWriter createBufferedWriter(String outputFileName) {
		try (FileOutputStream outputStream = new FileOutputStream(outputFileName);
				OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));) {

			return new BufferedWriter(streamWriter);

		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
		return null;
	}

	private BufferedReader createBufferedReader(String inputFileName) {
		try (FileInputStream inputStream = new FileInputStream(inputFileName);
				InputStreamReader streamReader = new InputStreamReader(inputStream, Charset.forName("ISO-8859-1"));) {

			return new BufferedReader(streamReader);

		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
		return null;
	}

}
