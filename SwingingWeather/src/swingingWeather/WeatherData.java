package swingingWeather;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

public class WeatherData {
	private String zipCode;
	private HashMap<String,Object> rawData;
	private HashMap<String,Object> data;

	
	public static void main(String[] args) {
		WeatherData d = new WeatherData("50111");
	}
	
	public WeatherData(String zip)
	{
		this.zipCode = zip;
		data =  new HashMap<String,Object>();
		update();
		
		System.out.println(rawData.keySet());
		//System.out.println(rawData);

		
		HashMap<String,Object> tempMap = (HashMap<String, Object>) rawData.get("main");
		
		System.out.println(tempMap.keySet());

		
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

		//tempMap = (HashMap<String, Object>) ((HashMap<String, Object>) rawData.get(0)).get("description");
		//data.put("description", tempMap.get("description"));
		
//		tempMap = (HashMap<String, Object>) rawData.get("sys");
//		data.put("country", tempMap.get("country"));
//		data.put("sunrise", tempMap.get("sunrise"));
//		data.put("sunset", tempMap.get("sunset"));
//		
//		data.put("name", rawData.get("name"));


		System.out.println(data);
		
		

		
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
