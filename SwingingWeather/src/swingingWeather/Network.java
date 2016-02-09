/**
 * 
 */
package swingingWeather;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * @author David Garner
 *
 */
public class WeatherData {
	private static String url = "http://api.openweathermap.org/data/2.5/weather";
	private static final String APIKEY = "ffddac062b6064aeee4aefc5662be9b8";
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws MalformedURLException, IOException, ParseException {
		// TODO Auto-generated method stub
		requestData("50525");

	}
	
	public static void requestData(String zipCode) throws MalformedURLException, IOException, ParseException{
		String myZipCode = zipCode.trim();
		String myCountryCode = "us";
		String encodingType = java.nio.charset.StandardCharsets.UTF_8.name(); //"UTF-8";
		
		if(myZipCode.length()!= 5 ){
			throw new IllegalArgumentException("ZipCode is invalid length");
		}
		try{
			Integer.parseInt(myZipCode);
		}catch(Exception e){
			throw new IllegalArgumentException("Zip code is not a number");
		}
		
		
		String query = String.format("zip=%s,%s&appid=%s", 
			     URLEncoder.encode(myZipCode, encodingType), 
			     URLEncoder.encode(myCountryCode, encodingType),
			     URLEncoder.encode(APIKEY, encodingType));
		url = url + "?" + query;
				
		URLConnection myConnection = new URL(url).openConnection();
		myConnection.setRequestProperty("Accept-Charset", encodingType);

		InputStream response = myConnection.getInputStream();
		
		System.out.println(myConnection.getContentType());
		
		byte [] myBuffer = new byte[response.available()];
		
		response.read(myBuffer);
		
		
		String outString = new String (myBuffer, "UTF-8");
	
		JSONParser jParse = new JSONParser();
		
		JSONObject data = (JSONObject)(jParse.parse(outString));
		
		//Need to check it there is another JSON Array in the Array
		JSONArray weather = (JSONArray) data.get("weather");
		
		
		JSONObject data2 = (JSONObject) weather.get(0);
		
		
		String weatherData = (String) data2.get("main");
		
		System.out.println(weatherData);

		
		
		//System.out.println("Weather " + weather.toJSONString());
		
		
		//System.out.println(outString);
		
		
		
		
		
	}
	

}
