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
	private HashMap<String,Object> data;

	
	public static void main(String[] args) {
		WeatherData d = new WeatherData("50111");
	}
	
	public WeatherData(String zip)
	{
		super();
		
		this.zipCode = zip;
		data =  new HashMap<String,Object>();
		update();
		
		HashMap<String,Object> tempMap = (HashMap<String, Object>) rawData.get("main");
				
		data.put("currentTemp", tempMap.get("temp"));
		data.put("pressure", tempMap.get("pressure"));
		data.put("humidity", tempMap.get("humidity"));
		data.put("minTemp", tempMap.get("temp_min"));
		data.put("maxTemp", tempMap.get("temp_max"));
		
		tempMap = (HashMap<String, Object>) rawData.get("wind");
		data.put("windSpeed", tempMap.get("speed"));
		data.put("windDeg", tempMap.get("deg"));
		
		tempMap = (HashMap<String, Object>) rawData.get("clouds");
		
		data.put("cloudCover", tempMap.get("all"));
		
		ArrayList<Object> obj = (ArrayList<Object>)rawData.get("weather");
		tempMap = (HashMap<String, Object>) obj.get(0);
		
		data.put("description", tempMap.get("description"));
		
		tempMap = (HashMap<String, Object>) rawData.get("sys");
		data.put("country", tempMap.get("country"));
		data.put("sunrise", tempMap.get("sunrise"));
		data.put("sunset", tempMap.get("sunset"));
		data.put("name", rawData.get("name"));
		
		System.out.println(this.getData());
	}
	
	public HashMap<String,Object> getData(){
		return this.data;
	}
	
	public void update(){
		try {
			 this.rawData = (HashMap)Network.requestData(zipCode);
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
	
}
