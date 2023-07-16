package Exercise1.Sources;

public class Person {
	private String firstName;
	private String lastName;
	private String birthDate;
	private String deceaseDate;

	public Person(String firstName, String lastName, String birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate.replace("b.", "");
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public void setBirthDate(String birthDate){
		this.birthDate = birthDate;
	}
	public void setDeceaseDate(String deceaseDate) {
		this.deceaseDate = deceaseDate;
	}

	String getFirstName(){
		return firstName;
	}
	String getLastName(){
		return lastName;
	}
	String getBirthDate(){
		return birthDate;
	}
	String getDeceaseDate(){
		return deceaseDate;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + " ("
				+ birthDate + " - " + deceaseDate + ")";
	}
};