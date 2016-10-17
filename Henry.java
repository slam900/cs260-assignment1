package assignment1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class Henry {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setLayout(new BorderLayout());
		f.setSize(1000, 800);
		
		JTabbedPane t = new JTabbedPane();
		
		JLabel[] labels = new JLabel[3];
		labels[0] = new JLabel("Author");
		labels[1] = new JLabel("Category");
		labels[2] = new JLabel("Publisher");
		
		for (JLabel l : labels) {
			l.setMinimumSize(new Dimension(100, 50));
			l.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		}
		
		t.addTab("Author", new SearchByAuthorPanel());
		t.addTab("Category", new SearchByCategoryPanel());
		t.addTab("Publisher", new SearchByPublisherPanel());
		t.setTabComponentAt(0, labels[0]);
		t.setTabComponentAt(1, labels[1]);
		t.setTabComponentAt(2, labels[2]);
		
		f.add(t);
		
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
