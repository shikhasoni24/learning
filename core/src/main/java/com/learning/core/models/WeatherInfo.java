package com.learning.core.models;

public class WeatherInfo {
	
    private String description;
    private double temp;
    private double speed;
    private String country;
    private int humidity;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	@Override
	public String toString() {
		return "WeatherInfo [description=" + description + ", temp=" + temp + ", speed=" + speed + ", country="
				+ country + ", humidity=" + humidity + "]";
	}


}
