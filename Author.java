package assignment1;

public class Author {
	private int number;
	private String lastName;
	private String firstName;
	// Author numbers 21 and 22 have no books
	// Don't allow selecting these authors
	
	public Author(int number, String lastName, String firstName) {
		this.number = number;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public int getNumber() {
		return number;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String toString() {
		return getFirstName() + " " + getLastName();
	}
	
}
