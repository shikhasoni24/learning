package com.learning.core.serviceimpl;

import java.io.IOException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.core.models.WeatherInfo;
import com.learning.core.service.WeatherService;
import com.learning.core.workflows.PopulateNewNode;

@Component(service = WeatherService.class, immediate = true)
public class WeatherServiceImpl implements WeatherService {
	private static HttpURLConnection connection;

public String getWeatherInfo(String city) {
		String str = null;
		try {

			URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city
					+ "&appid=6f8308ad62d456ca12d909309b10c023");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			InputStream responseStream = connection.getInputStream();//
			connection.getResponseCode();//----------------------------------this line

			if (responseStream != null) {
				str = IOUtils.toString(responseStream, "UTF-8");					
			}
//			ObjectMapper mapper = new ObjectMapper();
//			Map<String, Object> jsonMap = mapper.readValue(responseStream, Map.class);
//			str = jsonMap.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;

	}

	public WeatherInfo setWeatherInfo(String city) {
		String s = getWeatherInfo(city);
		WeatherInfo weatherInfo = new WeatherInfo();

		try {
			JSONObject obj = new JSONObject(s);
			if (obj.has("weather")) {
				JSONArray jsonArray = obj.getJSONArray("weather");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject ob = (JSONObject) jsonArray.get(i);
					if (ob.has("description")) {

						weatherInfo.setDescription(ob.getString("description"));
					}
}
				if (obj.has("main")) {
					JSONObject js = obj.getJSONObject("main");
					if (js.has("temp")) {
						weatherInfo.setTemp(js.getDouble("temp"));

					}
					if (js.has("humidity")) {
						weatherInfo.setHumidity(js.getInt("humidity"));

					}
				}
				if (obj.has("wind")) {
					JSONObject j = obj.getJSONObject("wind");
					if (j.has("speed")) {
						weatherInfo.setSpeed(j.getDouble("speed"));

					}

				}
				if (obj.has("sys")) {
					JSONObject j = obj.getJSONObject("sys");
					if (j.has("country")) {
						weatherInfo.setCountry(j.getString("country"));
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return weatherInfo;

	}

}
