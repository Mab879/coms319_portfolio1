/**
 * 
 */
package swingingWeather;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.*;
import java.util.ArrayList;

import org.json.simple.*;
import org.json.simple.parser.ParseException;



/**
 * @author David Garner
 *
 */
public class Network {
	private static String url = "http://api.openweathermap.org/data/2.5";
	
	private static final String APIKEY = "ffddac062b6064aeee4aefc5662be9b8";
	private ArrayList<JSONObject> dataList;
	private String zipCode;
	private String myCountryCode = "us";
	private String units = "imperial";
	private String encodingType = java.nio.charset.StandardCharsets.UTF_8.name(); //"UTF-8"; 
	private String forecastDays;
	
	public Network(String myZipcode){
		this.zipCode = myZipcode.trim();
		dataList = new ArrayList<JSONObject>();
		forecastDays = "2";

	}
	
	public ArrayList<JSONObject> requestData(){
		
		if(zipCode.length()!= 5 ){
			throw new IllegalArgumentException("ZipCode is invalid length");
		}
		try{
			Integer.parseInt(zipCode);
		}catch(Exception e){
			throw new IllegalArgumentException("Zip code is not a number");
		}
		
		requestCurrent();
		requestForecast();		
		
	
		
		
		return dataList;
	}
	
	private void requestCurrent(){
		try {
			String query = String.format("q=zip=%s,%s&units=%s&appid=%s", 
				     URLEncoder.encode(zipCode, encodingType), 
				     URLEncoder.encode(myCountryCode, encodingType),
				     URLEncoder.encode(units, encodingType),
				     URLEncoder.encode(APIKEY, encodingType));
			
			URLConnection myConnection = new URL(url + "/weather?" + query).openConnection();
			myConnection.setRequestProperty("Accept-Charset", encodingType);

			InputStreamReader requestReader = new InputStreamReader(myConnection.getInputStream());
						
			dataList.add((JSONObject) JSONValue.parseWithException(requestReader));
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	private void requestForecast(){
		
		try {
			String query = String.format("q=zip=%s,%s&units=%s&%s&appid=%s", 
				     URLEncoder.encode(zipCode, encodingType), 
				     URLEncoder.encode(myCountryCode, encodingType),
				     URLEncoder.encode(units, encodingType),
				     URLEncoder.encode(forecastDays, encodingType),
				     URLEncoder.encode(APIKEY, encodingType));
			
			URLConnection myConnection = new URL(url + "/forecast/daily?" + query).openConnection();
			myConnection.setRequestProperty("Accept-Charset", encodingType);

			InputStreamReader requestReader = new InputStreamReader(myConnection.getInputStream());
			
			dataList.add((JSONObject) JSONValue.parseWithException(requestReader));
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
		

}
