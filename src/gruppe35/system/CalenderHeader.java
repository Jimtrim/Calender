package gruppe35.system;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 * 
 * Headeren for kalenderen vår
 * 
 * */
public class CalenderHeader extends JPanel{
	
	private GridBagLayout gridBagLayout;
    private int week;
    private int year;
    private Calendar calendar;
    private JLabel[] headerLabels;
    private JLabel mon,tue,wen,thu,fri,sat,sun;
    
    public CalenderHeader(int year, int week) {
		// TODO Auto-generated constructor stub
    	gridBagLayout = new GridBagLayout();
    	
    	gridBagLayout.columnWidths = new int[] {3,4,5,6};//må fikses på :)
    	
    	gridBagLayout.rowWeights = new double[]{2};//må fikses..
    	
    	setLayout(gridBagLayout);
    	
    	this.year = year;
    	this.week = week;
    	
    	calendar = calendar.getInstance();
    	calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        
        GridBagConstraints constrains = new GridBagConstraints();
        constrains.insets = new Insets(0, 0, 0, 0);
        constrains.fill = GridBagConstraints.BOTH;
        constrains.anchor = GridBagConstraints.CENTER;
        constrains.gridy = 0;
        
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        mon = new JLabel("<html> Mandag"+"<i> (" + 
        		calendar.get(Calendar.DAY_OF_MONTH) + "." +
        		Integer.toString(calendar.get(Calendar.MONTH)+1) + ") </i></html>", SwingConstants.CENTER);
        constrains.gridx = 2;
        add(mon, constrains);

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        tue = new JLabel("Tuesday", SwingConstants.CENTER);
        constrains.gridx = 4;
        add(tue, constrains);
        
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        wen = new JLabel("Wednesday", SwingConstants.CENTER);
        constrains.gridx = 6;
        add(wen, constrains);
	}
    
    public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new CalenderHeader(2012, 11));
    	frame.setVisible(true);
    	frame.pack();
		
	}
}
