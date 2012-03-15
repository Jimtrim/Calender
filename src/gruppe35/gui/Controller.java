package gruppe35.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Controller extends JFrame {

	private JTabbedPane tabpane;
	private JPanel calendar, createAppointment;
	
	

	
	public Controller(){
		
		calendar = new CalendarView();
		createAppointment = new AppointmentInfo();
		
		tabpane = new JTabbedPane();
		tabpane.add("Calendar", calendar);
		tabpane.add("Ny Avtale", createAppointment);
		
		this.add(tabpane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(500,500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		
	}
	
	
	
	



	
}

