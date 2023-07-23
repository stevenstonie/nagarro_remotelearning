package com.nagarro.remotelearning;

import com.nagarro.remotelearning.classes.TranscoderFromLatin1;

public class TranscoderFromLatin1App {
    public static void main(String[] args) {
        String inputFileName = "latin1_input_file.txt";
        String outputFileName = "utf8_output_file.txt";
        TranscoderFromLatin1 transcoderObj = new TranscoderFromLatin1();

        transcoderObj.transcodeFromLatin1(inputFileName, outputFileName);
    }
}
