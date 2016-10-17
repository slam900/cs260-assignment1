package assignment1;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class SearchByAuthorPanel extends JPanel {
	
	private List<Book> books;  // For some reason just needs to be instance variable
	
	public SearchByAuthorPanel() {
		String URL = "jdbc:oracle:thin:@dario.cs.uwec.edu:1521:csdev";
		String username = "OLSONSD";
		String pass = "TJJ7Q8UY";
		HenryDAO dao = new HenryDAO(URL, username, pass);
		
		JComboBox<Author> authorBox = new JComboBox<Author>();
		
		// Fill author drop menu
		List<Author> authors = dao.getAuthorData();
		for (Author a : authors)
			authorBox.addItem(a);  // Big space here
		
		authorBox.setMinimumSize(new Dimension(150, 50));
		authorBox.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		
		JComboBox<Book> bookBox = new JComboBox<Book>();
		// Action listener to talk to DAO -- anonymous inner class		
		authorBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				bookBox.removeAllItems();
				books = dao.getBookData((Author)authorBox.getSelectedItem());
				for (Book b : books)
					bookBox.addItem(b);
			}
		});
		add(authorBox);  // Should this go here?

		bookBox.setMinimumSize(new Dimension(150, 50));
		bookBox.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		
		JTextArea price = new JTextArea();

		// Action listener to talk to DAO -- anonymous inner class
		bookBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				price.setText("" + dao.getPrice((Book)bookBox.getSelectedItem()));
			}
		});
		add(bookBox);
		
		price.setMinimumSize(new Dimension(150, 50));
		price.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		
		add(price);
		
		JTextArea location = new JTextArea("located here");
		location.setMinimumSize(new Dimension(400, 50));
		location.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		
		add(location);
	}

}
