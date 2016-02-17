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

public class WeatherViewController {
	private JButton goButton;
	private JButton pausePlayButton;
	private String myZipCode;
	private SwingWeatherMain myWeatherWindow;
	private WeatherDataParser weatherParser;
	private ArrayList<WeatherData> weatherDataList;

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

	public WeatherViewController(SwingWeatherMain main, JButton goBtn, JButton radarBtn) {
		myWeatherWindow = main;
		goButton = goBtn;
		goButton.addActionListener(new ButtonListener());
		pausePlayButton = radarBtn;
		pausePlayButton.addActionListener(new ButtonListener());

	}

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
	
	private void bindRadarData(Image[] radarImages){
		myWeatherWindow.setRadarImages(radarImages);
		myWeatherWindow.drawRadar();
	}


	class ButtonListener implements ActionListener {
		public ButtonListener() {}

		@Override
		public void actionPerformed(ActionEvent e) {
		
			System.out.println(e.getSource());
			
			if(e.getSource() == goButton){
				String zipPattern = "\\d\\d\\d\\d\\d";
				if (!Pattern.matches(zipPattern, myWeatherWindow.getZipCode())) {
					JOptionPane.showMessageDialog(null, "Invalid zip code.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					myZipCode = myWeatherWindow.getZipCode();
					//myWeatherWindow.getRadarPanel().stopLoop();

				}
	
				EventQueue.invokeLater(() -> {
	
		            try {
		            	 weatherParser = new WeatherDataParser(myZipCode);
		            	 bindData(weatherParser); 
		            } catch (Exception exception) {
		            	exception.printStackTrace();
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


	class RadarThread extends Thread {
		
		RadarThread(){
		}
	
	    public void run() {
	    	Image[] myRadarImages =null;
			try {
				myRadarImages = Network.requestRadar(Integer.valueOf(myZipCode));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	bindRadarData(myRadarImages);
	
	    }
	}
}
