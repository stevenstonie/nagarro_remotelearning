package Exercise1.src.main.java.com.nagarro.remotelearning;

import Exercise1.classes.Person;

public class Main {
	public static void main(String... args) {
		System.out.println("hallo welt. heute wir lernen ein bisschen java.\n");

		Person johnDoe = new Person("John", "Doe");
		System.out.println(johnDoe.getFirstName() + " " + johnDoe.getSurname());

		Person eduardMihaiNicusorBotina = new Person("Eduard Mihai Nicusor Botina");
		System.out.println(eduardMihaiNicusorBotina.getFirstName() + " " + eduardMihaiNicusorBotina.getSurname());
	}
}
