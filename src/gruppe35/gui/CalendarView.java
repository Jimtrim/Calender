package gruppe35.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarView extends JPanel {
	
	private JLabel label;
	private JFrame frame = new JFrame("Calendar");
	
	public CalendarView(){
		label = new JLabel("Welcome");
		add(label);
		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//		frame.pack();
//		frame.setSize(350,170);
//		frame.setResizable(false);
//		
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		int height = screenSize.height;
//		int width = screenSize.width;
//		frame.setSize(width/2, height/2);
//		
//		frame.setLocationRelativeTo(null);
	
	}
	
}
