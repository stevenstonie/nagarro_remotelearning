package Exercise1.src.classes;

public class ContainsLettersOrSymbolsException extends RuntimeException {
	private static String message;

	public ContainsLettersOrSymbolsException(String message) {
		ContainsLettersOrSymbolsException.message = message;
		exceptionMessage();
	}

	public static void exceptionMessage() {
		System.out.println(message + " contains letters or symbols.");
	}
}
