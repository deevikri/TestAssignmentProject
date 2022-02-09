package weatherTest.API;
//enum is special class in java which has collection of constants or  methods
public enum APIResources {
	
	getGeolocationAPI("/geo/1.0/direct"),
	getWeatherAPI("/data/2.5/weather"),
	getWeather1API("/data/2.5/weather");
	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}
	

}
