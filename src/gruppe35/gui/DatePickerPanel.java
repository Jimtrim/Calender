package gruppe35.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.Locale;


public class DatePickerPanel extends JFrame {

	private JPanel contentPane;
	private ObservingTextField dateField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatePickerPanel frame = new DatePickerPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DatePickerPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel dateLabel = new JLabel("Dato:");
		dateLabel.setBounds(10, 11, 32, 14);
		contentPane.add(dateLabel);
		
		dateField = new ObservingTextField();
		dateField.setBounds(41, 8, 86, 20);
		contentPane.add(dateField);
		dateField.setColumns(10);
		
		JButton chooseDateButton = new JButton("Velg dato");
		chooseDateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String lang = null;
				final Locale locale = getLocale(lang);
				DatePicker dp = new DatePicker(dateField, locale);
				// previously selected date
				Date selectedDate = dp.parseDate(dateField.getText());
				dp.setSelectedDate(selectedDate);
				dp.start(dateField);
			}
		});
		chooseDateButton.setBounds(130, 7, 89, 23);
		contentPane.add(chooseDateButton);
	}
	
	private Locale getLocale(String loc) {
		if (loc != null && loc.length() == 0)
			return new Locale(loc);
		else
			return Locale.US;
	};
}
