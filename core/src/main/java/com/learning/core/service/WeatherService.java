package com.learning.core.service;

import com.learning.core.models.WeatherInfo;

public interface WeatherService {
	
	public String getWeatherInfo(String city);
	public WeatherInfo setWeatherInfo(String city) ;


}
