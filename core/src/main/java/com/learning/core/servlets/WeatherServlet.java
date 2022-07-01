package com.learning.core.servlets;

import java.io.IOException;


import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.google.gson.Gson;
import com.learning.core.models.WeatherInfo;
import com.learning.core.service.WeatherService;

@Component(service=Servlet.class,immediate=true,
           property={
                   Constants.SERVICE_DESCRIPTION + "=weather Servlet","sling.servlet.paths=/bin/data/weather" ,
                   "sling.servlet.methods=" + HttpConstants.METHOD_GET})

public class WeatherServlet extends SlingSafeMethodsServlet {
	
	@Reference
	WeatherService weatherService;

    private static final long serialVersionUID = 1L;

    @Override
    protected void  doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
    	try {  
    		String city=req.getParameter("nameofcity");
        WeatherInfo info =weatherService.setWeatherInfo(city);
        Gson gson=new Gson();
		String s=gson.toJson(info);
		JSONObject js=new JSONObject();
		
			 js.put("user",s);
			 resp.getWriter().write(js.toString());
		} catch (JSONException e) {
			e.printStackTrace();
			
		}
        //resp.setContentType("application/json");
       
    }
}
