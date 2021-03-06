package swingingWeather;

import javax.swing.*;

import java.awt.Font;
import java.awt.Image;

import javafx.embed.swing.JFXPanel;
import javafx.application.Platform;

/**
 * The main window for the applicaiton
 * 
 * @author Matthew Burket and David Garner
 *
 */
public class SwingWeatherMain {
	
	/**
	 * The main frame of the application
	 */
	private JFrame frame;
	
	/**
	 * The text field the zip code is entered
	 */
	private JTextField zipCodeField;
	/**
	 * The many labels that data is displayed in
	 */
	private JLabel currentTempValue, currentWindLabel, currentWindSpeedValue, currentWindUnits, currentHumdityValue,
			currentPressureValue, currentObservation;
	/**
	 * The go button that is pressed to update the data
	 */
	private JButton btnGo;
	
	/**
	 * Button to pause on play the radar
	 */
	private JButton radarButton;
	
	/**
	 * An array of the forecast panels for the forecast section
	 */
	private ForecastPanel[] forecasts;
	
	/**
	 * The trend chart for highs and lows
	 */
	public TrendChart chart = new TrendChart();
	private final int forecastDays = 5;
	
	/**
	 * The panel to display radar images
	 */
	private ImagePanel radarPanel;

	/**
	 * Create the application window
	 */
	public SwingWeatherMain() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("SwingingWeather");

		JPanel currentPanel = new JPanel();
		currentPanel.setBounds(0, 0, 225, 61);
		frame.getContentPane().add(currentPanel);
		currentPanel.setLayout(null);

		currentTempValue = new JLabel("104", SwingConstants.RIGHT);
		currentTempValue.setFont(new Font("DejaVu Sans", Font.PLAIN, 24));
		currentTempValue.setBounds(8, 20, 59, 29);
		currentPanel.add(currentTempValue);

		JLabel lblF = new JLabel("°F");
		lblF.setBounds(65, 21, 22, 15);
		currentPanel.add(lblF);

		JLabel lblNewLabel = new JLabel("Current");
		lblNewLabel.setBounds(20, 6, 115, 15);
		currentPanel.add(lblNewLabel);

		currentWindLabel = new JLabel("W");
		currentWindLabel.setBounds(96, 6, 17, 15);
		currentPanel.add(currentWindLabel);

		currentWindSpeedValue = new JLabel("60", SwingConstants.RIGHT);
		currentWindSpeedValue.setBounds(65, 6, 90, 15);
		currentPanel.add(currentWindSpeedValue);

		currentWindUnits = new JLabel("MPH");
		currentWindUnits.setBounds(157, 6, 39, 15);
		currentPanel.add(currentWindUnits);

		JLabel currentHumidyLabel = new JLabel("H");
		currentHumidyLabel.setBounds(96, 20, 17, 15);
		currentPanel.add(currentHumidyLabel);

		currentHumdityValue = new JLabel("42.0", SwingConstants.RIGHT);
		currentHumdityValue.setBounds(106, 20, 59, 15);
		currentPanel.add(currentHumdityValue);

		JLabel currentHumidtyLabel = new JLabel("%");
		currentHumidtyLabel.setBounds(167, 21, 17, 15);
		currentPanel.add(currentHumidtyLabel);

		JLabel currentPressureLabel = new JLabel("P");
		currentPressureLabel.setBounds(96, 33, 17, 15);
		currentPanel.add(currentPressureLabel);

		currentPressureValue = new JLabel("29.92", SwingConstants.RIGHT);
		currentPressureValue.setBounds(106, 31, 49, 15);
		currentPanel.add(currentPressureValue);

		JLabel currentPressureUnit = new JLabel("mb");
		currentPressureUnit.setBounds(157, 31, 39, 15);
		currentPanel.add(currentPressureUnit);

		currentObservation = new JLabel("Clouds: few clouds");
		currentObservation.setBounds(20, 48, 176, 15);
		currentPanel.add(currentObservation);

		JLabel zipCodeLabel = new JLabel("Zip Code:");
		zipCodeLabel.setBounds(6, 447, 70, 15);
		frame.getContentPane().add(zipCodeLabel);

		zipCodeField = new JTextField(5);
		zipCodeField.setBounds(88, 445, 114, 19);
		frame.getContentPane().add(zipCodeField);
		zipCodeField.setColumns(5);

		btnGo = new JButton("GO");

		btnGo.setBounds(213, 442, 70, 25);
		frame.getContentPane().add(btnGo);

		JPanel trendsWrapper = new JPanel();
		trendsWrapper.setBounds(308, 21, 311, 190);
		frame.getContentPane().add(trendsWrapper);

		JFXPanel jfxPanel = new JFXPanel();
		trendsWrapper.add(jfxPanel);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				chart.initFX(jfxPanel);
			}
		});

		JLabel lblTrends = new JLabel("Trends");
		lblTrends.setBounds(308, 6, 70, 15);
		frame.getContentPane().add(lblTrends);

		JLabel lblDayFor = new JLabel("5 Day Forecast");
		lblDayFor.setBounds(308, 215, 102, 16);
		frame.getContentPane().add(lblDayFor);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(308, 243, 311, 182);
		frame.getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);

		forecasts = new ForecastPanel[forecastDays + 1];
		for (int i = 0; i < forecasts.length; i++) {
			forecasts[i] = new ForecastPanel("Day " + i);
			forecasts[i].setSize(110, 110);
			forecasts[i].setVisible(true);
			panel.add(forecasts[i]);
		}

		
	    radarPanel = new ImagePanel();
		radarPanel.setBounds(20, 130, 311, 400);
		radarPanel.setVisible(true);

		radarButton = new JButton("Pause");
		
		radarButton.setLocation(0, 0);
		radarButton.setVisible(true);
		radarPanel.add(radarButton);
		frame.getContentPane().add(radarPanel);

		frame.setVisible(true);


	}

	/**
	 *  Gets the go button
	 * @return the go button
	 */
	public JButton getGoButton() {
		return btnGo;
	}
	
	public JButton getPausePlayButton(){
		return radarButton;
	}

	public String getZipCode() {
		return zipCodeField.getText();
	}

	/**
	 * @param currentTempValue
	 *            the currentTempValue to set
	 */
	public void setCurrentTempValue(String currentTempValue) {
		this.currentTempValue.setText(currentTempValue);
	}

	/**
	 * @param currentWindLabel
	 *            the currentWindLabel to set
	 */
	public void setCurrentWindLabel(String currentWindLabel) {
		this.currentWindLabel.setText(currentWindLabel);
	}

	/**
	 * @param currentWindSpeedValue
	 *            the currentWindSpeedValue to set
	 */
	public void setCurrentWindSpeedValue(String currentWindSpeedValue) {
		this.currentWindSpeedValue.setText(currentWindSpeedValue);
	}

	/**
	 * @param d
	 *            the currentHumdityValue to set
	 */
	public void setCurrentHumdityValue(String d) {
		this.currentHumdityValue.setText(d);
	}

	/**
	 * @param currentPressureValue
	 *            the currentPressureValue to set
	 */
	public void setCurrentPressureValue(String currentPressureValue) {
		this.currentPressureValue.setText(currentPressureValue);
	}

	/**
	 * @param currentObservation
	 *            the currentObservation to set
	 */
	public void setCurrentObservation(String currentObservation) {
		this.currentObservation.setText(currentObservation);
	}

	/**
	 *
	 * @param high
	 */
	public void addHighToChart(double high) {
		chart.addHigh(high);
	}

	/**
	 *
	 * @param low
	 */
	public void addLowToChart(double low) {
		chart.addLow(low);
	}

	/**
	 * 
	 * @param low
	 * @param high
	 * @param description
	 * @param index
	 */
	public void setForecasts(String low, String high, String description, int index) {
		forecasts[index - 1].setHigh(high);
		forecasts[index - 1].setLow(low);
		forecasts[index - 1].setDescription(description);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getForecastDays() {
		return forecastDays;
	}
	
	public void setRadarImages(Image [] images){
		this.radarPanel.setImages(images);
	}
	public void drawRadar(){
		this.radarPanel.draw(frame.getGraphics());
	}
	
	public ImagePanel getRadarPanel(){
		return this.radarPanel;
	}
	
	public void toggleRadarButtonText(){
		if(this.radarButton.getText() == "Pause"){
			this.radarButton.setText("Play");
		}else
			this.radarButton.setText("Pause");
	}

}
