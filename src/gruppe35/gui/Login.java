package gruppe35.gui;

//import java.awt.Dimension;
//import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Login extends JPanel implements ActionListener {
	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	
	private JTextField username;
	private JTextField password;
	private JButton login;
	private SpringLayout layout;
	private static JFrame frame = new JFrame("Login");
	
	
	public Login(){
		layout = new SpringLayout();
		setLayout(layout);
		
		usernameLabel = new JLabel("Username:");
		passwordLabel = new JLabel("Password:");
		add(usernameLabel);
		add(passwordLabel);
		
		username = new JTextField("", 20);
		add(username);
		
		password = new JTextField("", 20);
		add(password);
		
		login = new JButton("Login");
		add(login);
		login.addActionListener(this);
		
		layout.putConstraint(SpringLayout.WEST, usernameLabel, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, usernameLabel, 30, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.NORTH, passwordLabel, 10, SpringLayout.SOUTH, usernameLabel);
		layout.putConstraint(SpringLayout.WEST, passwordLabel, 20, SpringLayout.WEST, this);
		
		layout.putConstraint(SpringLayout.NORTH, username, 30, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, username, 10, SpringLayout.EAST, usernameLabel);
		
		layout.putConstraint(SpringLayout.NORTH, password, 8, SpringLayout.SOUTH, username);
		layout.putConstraint(SpringLayout.WEST, password, 11, SpringLayout.EAST, passwordLabel);
		
		layout.putConstraint(SpringLayout.NORTH, login, 10, SpringLayout.SOUTH, password);
		layout.putConstraint(SpringLayout.WEST, login, 160, SpringLayout.WEST, this);
		
		
		
		
	}
	

	public static void main(String[] args) {
		Login login = new Login();
		
		
		frame.add(login);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		frame.setSize(350,170);
		frame.setResizable(false);
		
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		int height = screenSize.height;
//		int width = screenSize.width;
		  
		frame.setLocationRelativeTo(null);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispose();
		frame.setContentPane(new CalendarView());
		
	}
	
	
	
	
	
}
