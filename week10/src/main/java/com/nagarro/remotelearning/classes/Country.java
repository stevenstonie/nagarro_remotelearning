package com.nagarro.remotelearning.classes;

public class Country implements Comparable<Country> {
	private String name;
	private String capital;

	public Country(String name, String capital) {
		this.name = name;
		this.capital = capital;
	}

	public int compareTo(Country anotherCountry) {
		return this.name.compareTo(anotherCountry.name);
	}

	public String getName() {
		return this.name;
	}

	public String getCapital() {
		return this.capital;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", capital=" + capital + "]";
	}

	public static int compare(Country arg0, Country arg1) {
		return arg0.getCapital().compareTo(arg1.getCapital());
	}
}
