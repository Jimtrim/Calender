package gruppe35.gui;

import gruppe35.model.Appointment;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AppointmentInfo extends JPanel implements ActionListener {
    private JLabel descriptionLabel;
    private JTextField descriptionText;
    private JLabel dateLabel;
    private JTextField dateText;
    private JLabel startTimeLabel;
    private JTextField startTimeText;
    private JLabel endTimeLabel;
    private JTextField endTimeText;
    private JLabel placeLabel;
    private JTextField placeText;
    private JButton update;
    private JButton delete;
    private JButton cancel;
    
    private List<ActionListener> actionListeners;

    private Appointment appointment;

	public AppointmentInfo() {
		actionListeners = new ArrayList<ActionListener>();
		
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 3, 5, 0);
        c.weighty = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        
        descriptionLabel = new JLabel("Beskrivelse:");
        c.gridx = 0;
        c.gridy = 0;
        this.add(descriptionLabel,c);
        
        descriptionText = new JTextField();
        c.gridheight = 1;
        c.gridwidth = 7;
        c.gridx = 1;
        c.gridy = 0;
        this.add(descriptionText, c);
        
        c.gridwidth = 1;
        dateLabel = new JLabel("Dato:");
        c.gridx = 0;
        c.gridy = 1;
        this.add(dateLabel, c);
            
        dateText = new JTextField("dd.mm.yyyy");
        c.gridx = 1;
        this.add(dateText, c);
            
        startTimeLabel = new JLabel("Starttid:");
        c.gridx = 2;
        this.add(startTimeLabel, c);
        
        startTimeText = new JTextField("hh.mm");
        c.gridx = 3;
        this.add(startTimeText, c);
        
        endTimeLabel = new JLabel("Sluttid:");
        c.gridx = 4;
        this.add(endTimeLabel, c);
        
        endTimeText = new JTextField("hh.mm");
        c.gridx = 5;
        this.add(endTimeText, c);
        
        placeLabel = new JLabel("Sted:");
        c.gridx = 0;
        c.gridy = 2;
        this.add(placeLabel, c);
        
        placeText = new JTextField(10);
        c.gridx = 1;
        this.add(placeText, c);
        
        update = new JButton("Oppdater");
        update.addActionListener(this);
        c.gridx = 0;
        c.gridy = 3;
        this.add(update,c);
        
        delete = new JButton("Slett");
        delete.addActionListener(this);
        c.gridx = 1;
        this.add(delete,c);
        
        cancel = new JButton("Avbryt");
        cancel.addActionListener(this);
        c.gridx = 2;
        this.add(cancel,c);
	}
	
	
	public static void main(String[] args) {		
		JFrame jframe = new JFrame("TESTING");
		jframe.add(new AppointmentInfo());
		jframe.pack();
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
