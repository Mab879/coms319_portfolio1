package swingingWeather;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Font;

public class SwingWeatherMain {

	private JFrame frame;

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
		currentPanel.setBounds(0, 0, 292, 108);
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
	}
}
