package swingingWeather;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

public class WeatherDataParser {
	
	
	private static final long serialVersionUID = 1L;
	private String zipCode;
	private int daysRequested;
	private Network myNetwork;
	ArrayList<JSONObject> rawData;
	private ArrayList<WeatherData> parsedData;
	

	
	public static void main(String[] args) {
		new WeatherDataParser("50011");
	}
	
	
	public WeatherDataParser(String zip)
	{
		this.zipCode = zip;
		this.daysRequested = 1;
		
		myNetwork = new Network(zip);
		
		rawData = myNetwork.requestData();
		parsedData = new ArrayList<WeatherData>();
		
		parseCurrentWeatherData();
		parseForecastData();	
	}
	
	public ArrayList<WeatherData> getData(){
		return parsedData;
	}
	
	private void parseCurrentWeatherData()
	{
		HashMap<String, Object> currentData = (HashMap<String, Object>) rawData.get(0);

		HashMap<String,Number> tempMap = (HashMap<String, Number>) currentData.get("main");
		
		WeatherData weatherData = new WeatherData(zipCode);
		
		weatherData.setCurrentTemp(tempMap.get("temp").doubleValue());
		weatherData.setPressure(tempMap.get("pressure").doubleValue());
		weatherData.setHumidity(tempMap.get("humidity").doubleValue());
		weatherData.setMinTemp(tempMap.get("temp_min").doubleValue());
		weatherData.setMaxTemp(tempMap.get("temp_max").doubleValue());
		
		tempMap = (HashMap<String, Number>) currentData.get("wind");
		
		weatherData.setWindSpeed(tempMap.get("speed").doubleValue());
		weatherData.setWindAngle(tempMap.get("deg").doubleValue());
		
		tempMap = (HashMap<String, Number>) currentData.get("clouds");
		
		weatherData.setCloudCover(tempMap.get("all").doubleValue());
				
		ArrayList<Object> obj = (ArrayList<Object>)currentData.get("weather");
		HashMap<String, String> tempMapString = (HashMap<String, String>) obj.get(0);
		
		weatherData.setDescription((String) tempMapString.get("description"));
		
		tempMap = (HashMap<String, Number>) currentData.get("sys");
		tempMapString = (HashMap<String, String>) currentData.get("sys");

		
		weatherData.setCountry(tempMapString.get("country"));
		weatherData.setSunrise(tempMap.get("sunrise").doubleValue());
		weatherData.setSunset(tempMap.get("sunset").doubleValue());
		weatherData.setName((String) currentData.get("name"));
			
		parsedData.add(weatherData);
		
		System.out.println(weatherData);
		
	}
	
	private void parseForecastData(){

		for (int i=0; i<daysRequested; i++){
				
			HashMap<String, Object> currentData = (HashMap<String, Object>) rawData.get(1);
	
			daysRequested = ((Number) currentData.get("cnt")).intValue();
			ArrayList<Object> forecastData = (ArrayList<Object>) currentData.get("list");
			
			HashMap<String, Object> dayData = (HashMap<String, Object>) forecastData.get(i);
			
			HashMap<String, Number> tempMap = (HashMap<String, Number>) dayData.get("temp");
			
			WeatherData weatherData = new WeatherData(zipCode);
			
			weatherData.setMinTemp(tempMap.get("min").doubleValue());
			weatherData.setMaxTemp(tempMap.get("max").doubleValue());
			weatherData.setPressure(( (Number) dayData.get("pressure")).doubleValue());
			weatherData.setHumidity(( (Number) dayData.get("humidity")).doubleValue());
			weatherData.setWindSpeed(( (Number) dayData.get("speed")).doubleValue());
			weatherData.setWindAngle(( (Number) dayData.get("deg")).doubleValue());
			weatherData.setCloudCover(( (Number) dayData.get("clouds")).doubleValue());
			
			HashMap<String, String> tempMapString = (HashMap<String, String>) ((ArrayList<Object>) dayData.get("weather")).get(0);
	
			
			weatherData.setDescription(tempMapString.get("main"));
			
			System.out.println(weatherData);
	
			parsedData.add(weatherData);
		
		}
	}

}
	
