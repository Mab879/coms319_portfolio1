package swingingWeather;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.application.Platform;

import javax.swing.JButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

/**
 * Controller class to bind the data from the model to the view
 * @author David Garner and  Matthew Burket
 *
 */
public class WeatherViewController {
	private JButton goButton;
	private JButton pausePlayButton;
	private String myZipCode;
	private SwingWeatherMain myWeatherWindow;
	private WeatherDataParser weatherParser;
	private ArrayList<WeatherData> weatherDataList;

	/**
	 * Main method to run the program
	 * @param args
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			SwingWeatherMain window = null;
			try {
				window = new SwingWeatherMain();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (window != null) {
				new WeatherViewController(window, window.getGoButton(), window.getPausePlayButton());
			}

		});

	}

	/**
	 * @param main weather frame and panels to display on the view
	 * @param goBtn action button to load data to view
	 * @param radarBtn button used to toggle radar animation
	 */
	public WeatherViewController(SwingWeatherMain main, JButton goBtn, JButton radarBtn) {
		myWeatherWindow = main;
		goButton = goBtn;
		goButton.addActionListener(new ButtonListener());
		pausePlayButton = radarBtn;
		pausePlayButton.addActionListener(new ButtonListener());

	}

	/**
	 * Method that binds all of the text based data to the view, except a Weather Parser object to source the data
	 * @param WP WeatherParser object that contains data to bind to the view
	 */
	private void bindData(WeatherDataParser WP){
		weatherDataList = WP.getData();

		// Set current weather
		WeatherData weatherDay = weatherDataList.get(0);
		myWeatherWindow.setCurrentHumdityValue(String.valueOf(Math.round(weatherDay.getHumidity())));
		myWeatherWindow.setCurrentObservation(String.valueOf(weatherDay.getDescription()));
		myWeatherWindow.setCurrentPressureValue(String.valueOf(Math.round(weatherDay.getPressure())));
		myWeatherWindow.setCurrentTempValue(String.valueOf(Math.round(weatherDay.getCurrentTemp())));
		myWeatherWindow.setCurrentWindSpeedValue(String.valueOf(Math.round(weatherDay.getWindSpeed())));

		// Binds the forecast Data to the forecast Panels
		for (int i = 1; i <= myWeatherWindow.getForecastDays() + 1; i++) {
			weatherDay = weatherDataList.get(i);
			myWeatherWindow.setForecasts(String.valueOf(weatherDay.getMinTemp()),
					String.valueOf(weatherDay.getMaxTemp()), String.valueOf(weatherDay.getDescription()), i);

		}

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				myWeatherWindow.chart.clearChart();

				for (int i = 1; i <= myWeatherWindow.getForecastDays() + 1; i++) {

					WeatherData weatherDay = weatherDataList.get(i);
					myWeatherWindow.addHighToChart(weatherDay.getMaxTemp());
					myWeatherWindow.addLowToChart(weatherDay.getMinTemp());
				}
			}
		});

		
	}
	
	/**
	 * Binds the radar images to the View
	 * @param radarImages array of Images to be used for the radar animation
	 */
	private void bindRadarData(Image[] radarImages){
		if(radarImages != null){
			myWeatherWindow.setRadarImages(radarImages);
			myWeatherWindow.drawRadar();
		}
	}


	/**
	 * Action Listener that identifies which button is pressed and the performs that specified action
	 *
	 */
	class ButtonListener implements ActionListener {
		public ButtonListener() {}

		@Override
		public void actionPerformed(ActionEvent e) {
		
			
			//Checks for valid zipcode
			if(e.getSource() == goButton){
				String zipPattern = "\\d\\d\\d\\d\\d";
				if (!Pattern.matches(zipPattern, myWeatherWindow.getZipCode())) {
					JOptionPane.showMessageDialog(null, "Invalid zip code.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					myZipCode = myWeatherWindow.getZipCode();
				}
	
				EventQueue.invokeLater(() -> {
	
		            try {
		            	 weatherParser = new WeatherDataParser(myZipCode);
		            	 bindData(weatherParser);
		            	 
		            }catch (IOException ex){
						JOptionPane.showMessageDialog(null,"Please try your request again later. We have developer tier API access with a limited number of request/min.", "Error", JOptionPane.ERROR_MESSAGE);
		            }catch (Exception ex) {
		            	//ex.printStackTrace();
		            }
		        });
				
				 RadarThread rt = new RadarThread();
	        	 rt.start();
			}
			else if (e.getSource() == pausePlayButton){
				myWeatherWindow.getRadarPanel().toggleLoop();
				myWeatherWindow.toggleRadarButtonText();
			}
		}
	}

	
	/**
	 * Class used to display radae data on a seperate thread
	 * @author David Garner
	 *
	 */
	class RadarThread extends Thread {
		
		RadarThread(){
		}
	
	    public void run() {
	    	Image[] myRadarImages =null;
			try {
				myRadarImages = Network.requestRadar(Integer.valueOf(myZipCode));
			} catch (NumberFormatException e) {
				//e.printStackTrace();
			} catch (IOException e) {
				//e.printStackTrace();
			}catch(IllegalStateException e){
				JOptionPane.showMessageDialog(null, e.getMessage() + ".", "Error", JOptionPane.ERROR_MESSAGE);

			}
	    	bindRadarData(myRadarImages);
	
	    }
	}
}
