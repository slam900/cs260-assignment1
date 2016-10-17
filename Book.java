package assignment1;

public class Book {
	String code;
	String title;
	
	public Book(String code, String title) {
		this.code = code;
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String toString() {
		return getTitle();
	}
}
