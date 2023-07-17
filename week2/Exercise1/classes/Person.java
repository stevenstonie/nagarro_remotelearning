package Exercise1.classes;

public class Person {
	private String firstName;
	private String surname;

	public Person(String firstName, String surname) {
		this.firstName = firstName;
		this.surname = surname;
	}

	public Person(String fullName) {
		String[] names = fullName.split(" ");

		this.firstName = "";
		for (int i = 0; i < names.length - 1; i++) {
			this.firstName += names[i] + " ";
		}
		this.firstName = this.firstName.substring(0, this.firstName.length() - 1);
		this.surname = names[names.length - 1];
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getSurname() {
		return this.surname;
	}
};
