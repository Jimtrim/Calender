package gruppe35.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Controller extends JFrame implements PropertyChangeListener {

	public static JPanel currentGUI;
	public static JPanel lastGUI;
	public static boolean GUIChange = false;
	PropertyChangeSupport listener;
	
	public static void changeGUI(JPanel panel){
		lastGUI = currentGUI;
		currentGUI = panel;
		GUIChange = true;
		
	}
	
	
	
	public void setGUI(){
		if(GUIChange == true){
			this.add(currentGUI);
			GUIChange = false;
		}
	}
	
	public Controller(){
		currentGUI = new Login();
		this.add(currentGUI);
		
		
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(500,500);
		this.setResizable(false);
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		new Controller();
	}



	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		setGUI();
		
	}
	
}

