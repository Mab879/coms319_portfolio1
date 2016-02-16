package swingingWeather;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;

/**
 * 
 * This panel a forecast day
 *
 */
public class ForecastPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7792341260650257496L;
	private JLabel lblDay;
	private JLabel lblHigh;
	private JLabel lblHighValue;
	private JLabel lblLow;
	private JLabel lblLowValue;
	private JLabel lblDescription;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;

	public ForecastPanel(String title) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridLayout(0, 1, 0, 0));

		lblDay = new JLabel(title);
		lblDay.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		add(lblDay);

		label = new JLabel("");
		add(label);

		lblHigh = new JLabel("High");
		lblHigh.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		add(lblHigh);

		lblHighValue = new JLabel("104 ºF");
		add(lblHighValue);

		label_1 = new JLabel("");
		add(label_1);

		lblLow = new JLabel("Low");
		lblLow.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		add(lblLow);

		lblLowValue = new JLabel("104 ºF");
		add(lblLowValue);

		label_2 = new JLabel("");
		add(label_2);

		lblDescription = new JLabel("Clouds: Wind");
		add(lblDescription);

	}

	public void setTitle(String title) {
		lblDay.setText(title);
	}

	public void setHigh(String high) {
		lblHighValue.setText(high);
	}

	public void setLow(String low) {
		lblLowValue.setText(low);
	}

	public void setDescription(String desc) {
		lblDescription.setText(desc);
	}

}
