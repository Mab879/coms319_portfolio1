package swingingWeather;

public class WeatherData {
	private String country;
	private String zipCode;
	private double sunrise;
	private double sunset;
	private double currentTemp;
	private double minTemp;
	private double maxTemp;
	private double cloudCover;
	private String description;
	private double pressure;
	private double windSpeed;
	private double windAngle;
	private String name;
	private double humidity;
	
	
	
	public WeatherData(String zip){
		this.zipCode = zip;
	}
	
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the sunrise
	 */
	public double getSunrise() {
		return sunrise;
	}

	/**
	 * @param sunsrise the sunrise to set
	 */
	public void setSunrise(double sunrise) {
		this.sunrise = sunrise;
	}

	/**
	 * @return the sunset
	 */
	public double getSunset() {
		return sunset;
	}

	/**
	 * @param sunset the sunset to set
	 */
	public void setSunset(double sunset) {
		this.sunset = sunset;
	}

	/**
	 * @return the currentTemp
	 */
	public double getCurrentTemp() {
		return currentTemp;
	}

	/**
	 * @param d the currentTemp to set
	 */
	public void setCurrentTemp(double d) {
		this.currentTemp = d;
	}

	/**
	 * @return the minTemp
	 */
	public double getMinTemp() {
		return minTemp;
	}

	/**
	 * @param minTemp the  to set
	 */
	public void setMinTemp(double minTemp) {
		this.minTemp = minTemp;
	}

	/**
	 * @return the maxTemp
	 */
	public double getMaxTemp() {
		return maxTemp;
	}

	/**
	 * @param maxTemp the maxTemp to set
	 */
	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}

	/**
	 * @return the cloudCover
	 */
	public double getCloudCover() {
		return cloudCover;
	}

	/**
	 * @param cloudCover the cloudCover to set
	 */
	public void setCloudCover(double cloudCover) {
		this.cloudCover = cloudCover;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the pressure
	 */
	public double getPressure() {
		return pressure;
	}

	/**
	 * @param pressure the pressure to set
	 */
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	/**
	 * @return the windSpeed
	 */
	public double getWindSpeed() {
		return windSpeed;
	}

	/**
	 * @param windSpeed the windSpeed to set
	 */
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	/**
	 * @return the windAngle
	 */
	public double getWindAngle() {
		return windAngle;
	}

	/**
	 * @param windAngle the windAngle to set
	 */
	public void setWindAngle(double windAngle) {
		this.windAngle = windAngle;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the humidity
	 */
	public double getHumidity() {
		return humidity;
	}

	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WeatherData [country=" + country + ", zipCode=" + zipCode
				+ ", sunrise=" + sunrise + ", sunset=" + sunset
				+ ", currentTemp=" + currentTemp + ", minTemp=" + minTemp
				+ ", maxTemp=" + maxTemp + ", cloudCover=" + cloudCover
				+ ", description=" + description + ", pressure=" + pressure
				+ ", windSpeed=" + windSpeed + ", windAngle=" + windAngle
				+ ", name=" + name + ", humidity=" + humidity + "]";
	}
	
	
	
}
