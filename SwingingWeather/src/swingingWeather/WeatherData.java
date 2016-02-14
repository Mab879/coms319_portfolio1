package swingingWeather;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

public class WeatherData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String zipCode;
	private HashMap<String,Object> rawData;
	private HashMap<String,Object> currentData;
	private HashMap<String,Object> forecastData;
	private ArrayList<HashMap<String, Object>> data;
	private Network myNetwork;
	
	public static void main(String[] args) {
		new WeatherData("50111");
	}
	
	public WeatherData(String zip)
	{
		this.zipCode = zip;
		myNetwork = new Network(zip);
		currentData =  new HashMap<String,Object>();
		update();
	}
	
	public HashMap<String,Object> getData(){
		return this.currentData;
	}
	
	/**
	 * Requests Data from the OpenWeather API, then updates the current data and forecastData
	 */
	@SuppressWarnings("unchecked")
	public void update(){
		try {
			 this.rawData = (HashMap<String, Object>)myNetwork.requestData();
			 getCurrentData();
			 getForecastData();
		}
		catch (MalformedURLException e) {
			System.out.println("Url is inncorrect.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read stream.");
			e.printStackTrace();
		}
		
		
	}
	
	public void setZip(String zip){
		this.zipCode = zip;
	}
	
	public String getZip(){
		return zipCode;
	}
	
	@SuppressWarnings("unchecked")
	private void getCurrentData()
	{

		HashMap<String,Object> tempMap = (HashMap<String, Object>) rawData.get("main");
				
		currentData.put("currentTemp", tempMap.get("temp"));
		currentData.put("pressure", tempMap.get("pressure"));
		currentData.put("humidity", tempMap.get("humidity"));
		currentData.put("minTemp", tempMap.get("temp_min"));
		currentData.put("maxTemp", tempMap.get("temp_max"));
		
		tempMap = (HashMap<String, Object>) rawData.get("wind");
		currentData.put("windSpeed", tempMap.get("speed"));
		currentData.put("windDeg", tempMap.get("deg"));
		
		tempMap = (HashMap<String, Object>) rawData.get("clouds");
		
		currentData.put("cloudCover", tempMap.get("all"));
		
		ArrayList<Object> obj = (ArrayList<Object>)rawData.get("weather");
		tempMap = (HashMap<String, Object>) obj.get(0);
		
		currentData.put("description", tempMap.get("description"));
		
		tempMap = (HashMap<String, Object>) rawData.get("sys");
		currentData.put("country", tempMap.get("country"));
		currentData.put("sunrise", tempMap.get("sunrise"));
		currentData.put("sunset", tempMap.get("sunset"));
		currentData.put("name", rawData.get("name"));
		
		System.out.println(this.getData());
		
	}
	
	private void getForecastData(){
		
		
	}
	
}
