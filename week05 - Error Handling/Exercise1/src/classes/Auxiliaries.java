package classes;

public class Auxiliaries {
	public static boolean checkIfItsStringOfDigits(String string) {
		for (int i = 0; i < string.length(); i++) {
			if (!Character.isDigit(string.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
