package swingingWeather;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.application.Platform;

import javax.swing.JButton;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class WeatherViewController {
	private JButton goButton;
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

			if(window != null){
				new WeatherViewController (window,window.getGoButton());
			}

        });

	}

	public WeatherViewController(SwingWeatherMain main, JButton btn){
		myWeatherWindow = main;
		goButton = btn;
		goButton.addActionListener(new GoButtonActionListener());

	}

	private void bindData(WeatherDataParser WP){
		weatherDataList = WP.getData();

		WeatherData weatherDay = weatherDataList.get(0);
		myWeatherWindow.setCurrentHumdityValue(String.valueOf(Math.round(weatherDay.getHumidity())));
		myWeatherWindow.setCurrentObservation(String.valueOf(weatherDay.getDescription()));
		myWeatherWindow.setCurrentPressureValue(String.valueOf(Math.round(weatherDay.getPressure())));
		myWeatherWindow.setCurrentTempValue(String.valueOf(Math.round(weatherDay.getCurrentTemp())));

		//myWeatherWindow.setCurrentWindSpeedValue(String.valueOf(weatherDay.getWindSpeed()));
      

		//myWeatherWindow.setCurrentWindSpeedValue(String.valueOf(Math.round(weatherDay.getWindSpeed())));

		

		//Binds the forecast Data to the forecast Panels
				for(int i=1; i<=myWeatherWindow.getForecastDays()+1; i++){
					weatherDay = weatherDataList.get(i);
					myWeatherWindow.setForecasts(	
							String.valueOf(weatherDay.getMinTemp()), 
							String.valueOf(weatherDay.getMaxTemp()),
							String.valueOf(weatherDay.getDescription()), 
							i
					);
					
				}
				
				Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						myWeatherWindow.chart.clearChart();
						
						
						for(int i=1; i<=myWeatherWindow.getForecastDays()+1; i++){
							
							WeatherData weatherDay = weatherDataList.get(i);
							myWeatherWindow.addHighToChart(weatherDay.getMaxTemp());
							myWeatherWindow.addLowToChart(weatherDay.getMinTemp());
						}
					}
				});
	}


	class GoButtonActionListener implements ActionListener{


		public GoButtonActionListener(){

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String zipPattern = "\\d\\d\\d\\d\\d";
			if (!Pattern.matches(zipPattern, myWeatherWindow.getZipCode())) {
				JOptionPane.showMessageDialog(null, "Invalid zip code.", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
				myZipCode =  myWeatherWindow.getZipCode();
			}

			
			EventQueue.invokeLater(() -> {
	            try {
	            	 weatherParser = new WeatherDataParser(myZipCode);
	            	 bindData(weatherParser);
	            } catch (Exception exception) {
	            	exception.printStackTrace();
	            }
	        });

		}


	}

}
