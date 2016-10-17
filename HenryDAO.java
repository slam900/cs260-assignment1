package assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HenryDAO {
	private Connection connection;
	private Statement statement;
	private ResultSet results;
	
	private String URL;
	private String user;
	private String pass;
	
	public HenryDAO(String URL, String user, String pass) {
		this.URL = URL;
		this.user = user;
		this.pass = pass;
		
		connection = null;
		statement = null;
		results = null;
		
		try {
			connection = DriverManager.getConnection(URL, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Author> getAuthorData() {
		List<Author> authors = new ArrayList<Author>();
		// Execute query on database
		
		// HARDCODE
		// Eliminate authors with no books
		String query = "SELECT * FROM henry_author a\n"
				+ "WHERE author_num IN (\n"
				+ "SELECT author_num FROM henry_wrote)\n"
				+ "ORDER BY author_last";
		try {
			statement = connection.createStatement();
			results = statement.executeQuery(query);
			
			while (results.next()) {
				int authorNumber = results.getInt("author_num");
				String lastName = results.getString("author_last");
				String firstName = results.getString("author_first");
				authors.add(new Author(authorNumber, lastName, firstName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authors;
	}
	
	public List<Book> getBookData(Author author) {
		List<Book> books = new ArrayList<Book>();
		System.out.println("Author: " + author);
		System.out.println();
		String query = "SELECT * FROM henry_book b\n"
				+ "JOIN henry_wrote w ON w.book_code = b.book_code\n"
				+ "WHERE author_num = " + author.getNumber();
		try {
			statement = connection.createStatement();
			results = statement.executeQuery(query);
			
			while (results.next()) {
				String code = results.getString("book_code");
				String title = results.getString("title");
				books.add(new Book(code, title));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	
	public double getPrice(Book book) {
		double price = -1;
		System.out.println("Book: " + book);
		System.out.println();
		
		String query = "SELECT price FROM henry_book\n"
				+ "WHERE title = '" + book.getTitle() + "'";
//		System.out.println(query);
		try {
			statement = connection.createStatement();
			results = statement.executeQuery(query);
			results.next();  // Just need this??
			price = results.getDouble("price");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return price;
	}
}
