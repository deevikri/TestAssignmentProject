package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "weather" })

public class GetWeatherinfo {		
	

	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;

	}		
		public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}

		public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

		public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}

		private String base;
		private Main main;
		private String visibility;		
		private String name;
		private String cod;
		private Coord coord;
		public Coord getCoord() {
			return coord;
		}
		public void setCoord(Coord coord) {
			this.coord = coord;
		}
		public String getWeather() {
			return weather;
		}
		public void setWeather(String weather) {
			this.weather = weather;
		}
		public Wind getWind() {
			return wind;
		}
		public void setWind(Wind wind) {
			this.wind = wind;
		}
		public Clouds getClouds() {
			return clouds;
		}
		public void setClouds(Clouds clouds) {
			this.clouds = clouds;
		}
		public Sys getSys() {
			return sys;
		}
		public void setSys(Sys sys) {
			this.sys = sys;
		}
		public String getDt() {
			return dt;
		}
		public void setDt(String dt) {
			this.dt = dt;
		}
		public String getTimezone() {
			return timezone;
		}
		public void setTimezone(String timezone) {
			this.timezone = timezone;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

		private String weather;
		private Wind wind;
		private Clouds clouds;
		private Sys sys;
		private String dt;
		private String timezone;
		private String id;
		
		
		

		
	

}
