package swingingWeather;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class ForecastPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7792341260650257496L;

	private JLabel lblDay;
	
	private JLabel lblHigh;
	
	private JLabel lblHighValue;
	
	private JLabel lblLow;
	
	private JLabel lblLowtValue;
	
	private JLabel lblDescription;
	
	
	public ForecastPanel(String title) {
		setLayout(null);
		
		lblDay = new JLabel(title);
		lblDay.setBounds(39, 6, 61, 16);
		add(lblDay);
		
		lblHigh = new JLabel("High");
		lblHigh.setBounds(6, 34, 61, 16);
		add(lblHigh);
		
		lblHighValue = new JLabel("104 ºF");
		lblHighValue.setBounds(49, 34, 61, 16);
		add(lblHighValue);
		
		lblLow = new JLabel("Low");
		lblLow.setBounds(6, 49, 61, 16);
		add(lblLow);
		
		lblLowtValue = new JLabel("104 ºF");
		lblLowtValue.setBounds(49, 49, 61, 16);
		add(lblLowtValue);
		
		lblDescription = new JLabel("Clouds: Wind");
		lblDescription.setBounds(6, 78, 94, 16);
		add(lblDescription);

	}
	
	public void setTitle(String title) {
		lblDay.setText(title);
	}
	
	public void setHigh(String high) {
		lblHigh.setText(high);
	}
	
	public void setLow(String low) {
		lblLow.setText(low);
	}
	
	public void lblDescription(String desc) {
		lblDescription.setText(desc);
	}

}
