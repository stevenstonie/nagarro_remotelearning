package Exercise1.src.main.java.com.nagarro.remotelearning;

import java.util.Scanner;

import Exercise1.src.classes.ContainsLettersOrSymbolsException;

public class Main {
	private static int fromStringToInt(String input) {
		if (!classes.Auxiliaries.checkIfItsStringOfDigits(input)) {
			throw new ContainsLettersOrSymbolsException(input);
		}

		int result = Integer.parseInt(input);
		return result;
	}

	public static void main(String... args) {
		Scanner console = new Scanner(System.in);

		System.out.println("enter a number to convert to int: ");
		String input = console.nextLine();

		int int_input;
		int_input = fromStringToInt(input);
		System.out.println(int_input);

		console.close();
	}
}
