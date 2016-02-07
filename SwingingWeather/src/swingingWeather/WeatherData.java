/**
 * 
 */
package swingingWeather;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * @author David Garner
 *
 */
public class WeatherData {
	private static String url = "https://api.openweathermap.org/data/2.5/weather";
	private static final String APIKEY = "ffddac062b6064aeee4aefc5662be9b8";
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		requestData("94040");

	}
	
	public static void requestData(String zipCode) throws MalformedURLException, IOException{
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
		
		String myUrl = "http://api.openweathermap.org/data/2.5/weather?zip=94040,us&appid=44db6a862fba0b067b1930da0d769e98";
		
		URLConnection myConnection = new URL(myUrl).openConnection();
		myConnection.setRequestProperty("Accept-Charset", encodingType);

		InputStream response = myConnection.getInputStream();
		
		System.out.println(myConnection.getContentType());
		
		byte [] myBuffer = new byte[response.available()];
		
		response.read(myBuffer);
		String outString = new String (myBuffer, "UTF-8");
		
		System.out.println(outString);
		
		
		
		
		
	}
	

}
