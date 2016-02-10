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

import org.json.simple.*;
import org.json.simple.parser.ParseException;



/**
 * @author David Garner
 *
 */
public class Network {
	private static String url = "http://api.openweathermap.org/data/2.5/weather";
	private static final String APIKEY = "ffddac062b6064aeee4aefc5662be9b8";
	

	
	public static Object requestData(String zipCode) throws MalformedURLException, IOException{
		String myZipCode = zipCode.trim();
		String myCountryCode = "us";
		String units = "imperial";
		String encodingType = java.nio.charset.StandardCharsets.UTF_8.name(); //"UTF-8";
		
		if(myZipCode.length()!= 5 ){
			throw new IllegalArgumentException("ZipCode is invalid length");
		}
		try{
			Integer.parseInt(myZipCode);
		}catch(Exception e){
			throw new IllegalArgumentException("Zip code is not a number");
		}
		
		//Encode query 
		String query = String.format("q=zip=%s,%s&units=%s&appid=%s", 
			     URLEncoder.encode(myZipCode, encodingType), 
			     URLEncoder.encode(myCountryCode, encodingType),
			     URLEncoder.encode(units, encodingType),
			     URLEncoder.encode(APIKEY, encodingType));
		
		URLConnection myConnection = new URL(url + "?" + query).openConnection();
		myConnection.setRequestProperty("Accept-Charset", encodingType);

		InputStreamReader requestReader = new InputStreamReader(myConnection.getInputStream());
		
		Object data = null;
		
		try {
			data = JSONValue.parseWithException(requestReader);
		} catch (ParseException e) {
			System.out.print("Could not parse data from inputstream");
			e.printStackTrace();
		}
		
		
		return data;
	}
	
	public static Object requestData(String zip, int numDay){
		return numDay;
		
	}
	

}
