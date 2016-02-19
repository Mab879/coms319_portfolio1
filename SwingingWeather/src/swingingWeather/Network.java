
package swingingWeather;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.json.simple.*;
import org.json.simple.parser.ParseException;

/**
 *
 * The Network class is responsible for making network request.
 * @author David Garner
 *
 */
public class Network {
	//URLS used to request data
	private static String url = "http://api.openweathermap.org/data/2.5";
	private static String imageString = "http://api.wunderground.com/api/c1c7ff74994b286d/radar/"; 
	
	
	private static final String APIKEY_OPENWEATHER = "ffddac062b6064aeee4aefc5662be9b8";
	private ArrayList<JSONObject> dataList;
	private String zipCode;
	private String myCountryCode = "us";
	private String units = "imperial";
	private String encodingType = java.nio.charset.StandardCharsets.UTF_8.name(); //"UTF-8"; 
	private String forecastDays;
	private static long lastRadarRequest =-1;
	
	/**
	 * Gets data for the given zipcode
	 * @param myZipcode zipcode with the data 
	 */
	public Network(String myZipcode){
		this.zipCode = myZipcode.trim();
		dataList = new ArrayList<JSONObject>();
		forecastDays = "5";
	}
	
	
	/**
	 * Function that checks for valid zipcode and then calls the requestCurrent() and requestForeCast() methods
	 * @return ArrayList<JSONObject> the current data and forecast data 
	 * @throws IOException 
	 */
	public ArrayList<JSONObject> requestData() throws IOException{
		
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
	
	/**
	 * Static method that returns an Array of Images to be displayed at the radar
	 * @param zip
	 * @return
	 * @throws IOException
	 */
	public static Image [] requestRadar(int zip) throws IOException{
		long changeInTime = 0;
		if(lastRadarRequest==-1){
			
			changeInTime = 60000;
		}else{
			changeInTime = System.currentTimeMillis()-lastRadarRequest;
		}
		
		//Check for over loaded request
		if(changeInTime < 60000 ){
			throw new IllegalStateException("Must Wait 1 minute to call network for radar Images");
		}else{
			lastRadarRequest = System.currentTimeMillis();

			
		
		
		String myEncodingType = java.nio.charset.StandardCharsets.UTF_8.name(); //"UTF-8"; 
		String radarZip = String.valueOf(zip);
		String radius= "150";
		String widthHeight="250";
		int frame =1;
		
		
		Image [] radarImage = new Image[6];
		
		for(int i=0; i<6; i++){
		
			try {
				String query = String.format("q/%s.png?&radius=%s&width=%s&height=%s&newmaps=1&frame=%s", 
					     URLEncoder.encode(radarZip, myEncodingType), 
					     URLEncoder.encode(radius, myEncodingType),
					     URLEncoder.encode(widthHeight, myEncodingType),
					     URLEncoder.encode(widthHeight, myEncodingType),
					     URLEncoder.encode(String.valueOf(frame), myEncodingType));
						
						URL imageUrl = new URL(imageString+query);
						radarImage[i] = ImageIO.read(imageUrl);
						frame++;
					   
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println(Arrays.toString(radarImage));
		return radarImage;
		}
	}
	

	/**
	 * Method that adds the current weather to the dataList
	 */
	private void requestCurrent(){
		try {
			String query = String.format("q=zip=%s,%s&units=%s&appid=%s", 
				     URLEncoder.encode(zipCode, encodingType), 
				     URLEncoder.encode(myCountryCode, encodingType),
				     URLEncoder.encode(units, encodingType),
				     URLEncoder.encode(APIKEY_OPENWEATHER, encodingType));
			
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
	
	/**
	 * Method that adds the forecastData to the dataList
	 * @throws IOException 
	 */
	private void requestForecast() throws IOException{
		
		try {
			String query = String.format("q=zip=%s,%s&units=%s&%s&appid=%s", 
				     URLEncoder.encode(zipCode, encodingType), 
				     URLEncoder.encode(myCountryCode, encodingType),
				     URLEncoder.encode(units, encodingType),
				     URLEncoder.encode(forecastDays, encodingType),
				     URLEncoder.encode(APIKEY_OPENWEATHER, encodingType));
			
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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
