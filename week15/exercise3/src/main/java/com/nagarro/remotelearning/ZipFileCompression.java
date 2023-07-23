package com.nagarro.remotelearning;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFileCompression {
	public void zipTheFile(String inputFileName, String outputZipName) {
		try (FileInputStream inputStream = new FileInputStream(inputFileName);
				FileOutputStream outputStream = new FileOutputStream(outputZipName);
				ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
			ZipEntry zipEntry = new ZipEntry("compressed_file.txt");
			zipOutputStream.putNextEntry(zipEntry);

			byte[] buffer = new byte[1024];
			int bytesRead;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				zipOutputStream.write(buffer, 0, bytesRead);
			}

			zipOutputStream.closeEntry();
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
	}

	public String readZipFile(String outputZipName) {
		StringBuilder fileContents = new StringBuilder();
		String string = "";

		try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(outputZipName))) {
			@SuppressWarnings("unused")
			ZipEntry entry;
			while ((entry = zipInputStream.getNextEntry()) != null) {
				int size;
				byte[] buffer = new byte[1024];
				while ((size = zipInputStream.read(buffer, 0, buffer.length)) != -1) {
					string = new String(buffer, 0, size);
					fileContents.append(string);
				}
				zipInputStream.closeEntry();
			}
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
		return fileContents.toString();
	}
}
