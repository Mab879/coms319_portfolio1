package swingingWeather;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class SwingWeatherMain {

	private JFrame frame;
	private JTextField zipCodeField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingWeatherMain window = new SwingWeatherMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingWeatherMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 562, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel currentPanel = new JPanel();
		currentPanel.setBounds(0, 0, 225, 61);
		frame.getContentPane().add(currentPanel);
		currentPanel.setLayout(null);
		
		JLabel currentTempValue = new JLabel("104");
		currentTempValue.setFont(new Font("DejaVu Sans", Font.PLAIN, 24));
		currentTempValue.setBounds(20, 20, 59, 29);
		currentPanel.add(currentTempValue);
		
		JLabel lblF = new JLabel("°F");
		lblF.setBounds(65, 21, 60, 15);
		currentPanel.add(lblF);
		
		JLabel lblNewLabel = new JLabel("Current");
		lblNewLabel.setBounds(20, 6, 115, 15);
		currentPanel.add(lblNewLabel);
		
		JLabel currentWindLabel = new JLabel("W");
		currentWindLabel.setBounds(96, 6, 70, 15);
		currentPanel.add(currentWindLabel);
		
		JLabel currentWindSpeedValue = new JLabel("60");
		currentWindSpeedValue.setBounds(125, 6, 70, 15);
		currentPanel.add(currentWindSpeedValue);
		
		JLabel currentWindUnits = new JLabel("MPH");
		currentWindUnits.setBounds(147, 6, 70, 15);
		currentPanel.add(currentWindUnits);
		
		JLabel currentHumidyLabel = new JLabel("H");
		currentHumidyLabel.setBounds(96, 20, 70, 15);
		currentPanel.add(currentHumidyLabel);
		
		JLabel currentHumdityValue = new JLabel("42");
		currentHumdityValue.setBounds(125, 20, 70, 15);
		currentPanel.add(currentHumdityValue);
		
		JLabel currentHumidtyLabel = new JLabel("%");
		currentHumidtyLabel.setBounds(147, 21, 70, 15);
		currentPanel.add(currentHumidtyLabel);
		
		JLabel currentPressureLabel = new JLabel("P");
		currentPressureLabel.setBounds(96, 33, 70, 15);
		currentPanel.add(currentPressureLabel);
		
		JLabel currentPressureValue = new JLabel("29.92");
		currentPressureValue.setBounds(125, 31, 70, 15);
		currentPanel.add(currentPressureValue);
		
		JLabel currentPressureUnit = new JLabel("mb");
		currentPressureUnit.setBounds(168, 31, 70, 15);
		currentPanel.add(currentPressureUnit);
		
		JLabel currentObservation = new JLabel("Clouds: few clouds");
		currentObservation.setBounds(20, 48, 176, 15);
		currentPanel.add(currentObservation);
		
		JLabel zipCodeLabel = new JLabel("Zip Code:");
		zipCodeLabel.setBounds(12, 341, 70, 15);
		frame.getContentPane().add(zipCodeLabel);
		
		zipCodeField = new JTextField(5);
		zipCodeField.setBounds(94, 339, 114, 19);
		frame.getContentPane().add(zipCodeField);
		zipCodeField.setColumns(5);
		
		JButton btnGo = new JButton("GO");
		btnGo.addActionListener(e -> {
			String zipPattern = "\\d\\d\\d\\d\\d";
			if (!Pattern.matches(zipPattern, zipCodeField.getText())) {
				JOptionPane.showMessageDialog(null, "Invalid zip code.", "Error", JOptionPane.ERROR_MESSAGE);		
			}
		});
		
		btnGo.setBounds(219, 336, 70, 25);
		frame.getContentPane().add(btnGo);
		
		JPanel trendsWrapper = new JPanel();
		trendsWrapper.setBounds(237, 27, 311, 182);
		frame.getContentPane().add(trendsWrapper);
		
	
		
		JLabel lblTrends = new JLabel("Trends");
		lblTrends.setBounds(269, 12, 70, 15);
		frame.getContentPane().add(lblTrends);
	
	}
}
