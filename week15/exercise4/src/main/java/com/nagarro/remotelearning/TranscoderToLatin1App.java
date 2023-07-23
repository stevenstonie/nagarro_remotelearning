package com.nagarro.remotelearning;

import com.nagarro.remotelearning.classes.TranscoderToLatin1;
import com.nagarro.remotelearning.classes.Utils;

public class TranscoderToLatin1App {
	public static void main(String... args) {
		Utils.setConsoleEncodingToUTF8();
		String inputFileName = "utf8_input_file.txt";
		String outputFileName = "latin1_output_file.txt";
		String outputFileName2 = "non-latin_output_file.txt";
		TranscoderToLatin1 transcoderObj = new TranscoderToLatin1();

		transcoderObj.transcode(inputFileName, outputFileName, outputFileName2);
	}
}
