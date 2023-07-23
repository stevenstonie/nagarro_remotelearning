package com.nagarro.remotelearning.classes;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Utils {
	public static void setConsoleEncodingToUTF8() {
		try {
			System.setOut(new PrintStream(System.out, true, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncodingException: " + e.getMessage());
		}
	}
}
