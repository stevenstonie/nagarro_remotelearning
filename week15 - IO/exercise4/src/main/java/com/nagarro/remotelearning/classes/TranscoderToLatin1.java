package com.nagarro.remotelearning.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class TranscoderToLatin1 {
	public void transcode(String inputFileName, String outputFileName, String outputFileName2) {
		try {
			BufferedReader reader = createNewUTF8Reader(inputFileName);
			BufferedWriter writer = createNewWriter(outputFileName, "ISO-8859-1");
			BufferedWriter writer2 = createNewWriter(outputFileName2, "UTF-8");

			String line;
			System.out.println("characters that cannot be transcoded to latin1: ");
			while ((line = reader.readLine()) != null) {
				for (char c : line.toCharArray()) {
					if (c >= 0x00 && c <= 0xFF) {
						writer.write(c);
					} else {
						writer2.write(c);
						System.out.print(c);
					}
				}

				writer.newLine();
				writer2.newLine();
			}

			closeReadersAndWriters(reader, writer, writer2);
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
	}

	private static void closeReadersAndWriters(BufferedReader reader, BufferedWriter writer, BufferedWriter writer2) {
		try {
			reader.close();
			writer.close();
			writer2.close();
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
	}

	private static BufferedWriter createNewWriter(String outputFileName, String encoding) {
		try {
			FileOutputStream outputStream = new FileOutputStream(outputFileName);
			OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, Charset.forName(encoding));
			return new BufferedWriter(streamWriter);
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
		return null;
	}

	private static BufferedReader createNewUTF8Reader(String inputFileName) {
		try {
			FileInputStream inputStream = new FileInputStream(inputFileName);
			InputStreamReader streamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
			return new BufferedReader(streamReader);
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
		return null;
	}

}
