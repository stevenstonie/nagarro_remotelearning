package com.nagarro.remotelearning.week1ex1;

public class Person {
	private String firstName;
	private String lastName;
	private String birthYear;
	private String deceaseYear;

	public Person(String firstName, String lastName, String birthYear, String deceaseDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthYear = birthYear != null ? birthYear.replace("b.", "") : null;
		this.deceaseYear = deceaseDate != null ? deceaseDate : null;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBirthYear(String birthDate) {
		this.birthYear = birthDate;
	}

	public void setDeceaseYear(String deceaseDate) {
		this.deceaseYear = deceaseDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public String getDeceaseYear() {
		return deceaseYear;
	}

	@Override
	public String toString() {
		if (deceaseYear == null)
			return firstName + " " + lastName + " b." + birthYear;
		else
			return firstName + " " + lastName + " ("
					+ birthYear + " - " + deceaseYear + ")";
	}
};