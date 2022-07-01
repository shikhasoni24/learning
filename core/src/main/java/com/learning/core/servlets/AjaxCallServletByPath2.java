package com.learning.core.servlets;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javax.servlet.Servlet;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class, property = {"sling.servlet.paths=/bin/anydata/data",
//"sling.servlet.resourceTypes=learning/components/structure/page",
"sling.servlet.selector=customselectorone",
"sling.servlet.extensions=html", 
"sling.servlet.methods=GET" })
public class AjaxCallServletByPath2 extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;
	private static Logger LOG = LoggerFactory.getLogger(AjaxCallServletByPath2.class);

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
		try {
			String url = "https://randomuser.me/api/";
			String firstName = request.getParameter("fname");
			String lastName = request.getParameter("lname");
			LOG.info("fname is" + firstName);
			LOG.info("lname is" + lastName);

//			HttpResponse responseContent = null;
//			HttpClient aemClient = HttpClientBuilder.create().build();
//			HttpGet getCall = new HttpGet(url);
//			responseContent = aemClient.execute(getCall);
//			StringBuilder responseData = new StringBuilder();
//			responseData = processJsonResponse(responseContent);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("datavalue", firstName+lastName);
			//response.setContentType("application/json");
			response.getWriter().write(jsonObject.toString() +"ajaxServlet2");


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static StringBuilder processJsonResponse(HttpResponse response)
			throws UnsupportedEncodingException, UnsupportedOperationException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
		StringBuilder jsonResponse = new StringBuilder();
		String inputStream;
		while ((inputStream = br.readLine()) != null) {
			jsonResponse.append(inputStream);
		}
		br.close();
		return jsonResponse;
	}

}