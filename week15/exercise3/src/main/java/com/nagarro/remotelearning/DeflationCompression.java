package com.nagarro.remotelearning;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class DeflationCompression {
    public void deflateTheFile(String inputFileName, String outputFileName) {
        try (InputStream inputStream = new FileInputStream(inputFileName);
                OutputStream outputStream = new FileOutputStream(outputFileName);
                DeflaterOutputStream deflater = new DeflaterOutputStream(outputStream)) {

            byte[] buffer = new byte[1024]; // set a buffer of 1KB so in case of a 4gb file, it wouldnt occupy the memory all at once.
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                deflater.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public String readDeflatedFile(String outputFileName) {
        try (InputStream inputStream = new FileInputStream(outputFileName);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                InflaterInputStream inflater = new InflaterInputStream(inputStream)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inflater.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            return outputStream.toString();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            return null;
        }
    }
}
