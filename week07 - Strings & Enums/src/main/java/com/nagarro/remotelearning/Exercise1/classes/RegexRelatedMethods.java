package com.nagarro.remotelearning.Exercise1.classes;

public class RegexRelatedMethods {
	public static boolean validateEmail(String input) {
		// ^ - start of the string; [] - set of characters to choose one from; + - one or more of the preceding character;
		// @ - literal @; [.] - literal .; . - any character; ? - preceding character is optional $ - end of the string;
		return input.matches(".+@[a-zA-Z0-9]+[.]...?");
	}

	public static boolean validatePassword(String input) {
		// (?>.{10,})(?>.*[A-Z].*)(?>.*[a-z].*)(?>.*[!\"#$%&'()*+,-./:;<=>?@\\^_`{|}~].*)   <-- atomic grouping doesnt work
		// ^(?=.{10,}$)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*]).*$   <-- this also doesnt work
		return input.matches(".{10,}") && input.matches(".*[A-Z].*") && input.matches(".*[a-z].*")
				&& input.matches(".*[!\"#$%&'()*+,-./:;<=>?@\\^_`{|}~].*");
	}

	public static boolean validatePhoneNumber(String input) {
		return input.matches("(00[0-9]{11,})|([+][0-9]{11,})|([0-9]{10,})");
	}
}
