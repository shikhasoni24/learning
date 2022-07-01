package com.learning.core.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.service.component.annotations.Component;

@SuppressWarnings("deprecation")
@Component(service = Servlet.class, immediate = true, property = { "sling.servlet.paths=/bin/getEmployee",
		"sling.servlet.methods=GET" })
public class EmployeeInfoServlet extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

		JSONObject employeeJson = new JSONObject();
		try {
			// employeeJson.put(“EmployeeFirst”, “Employee Last”);

			// JSON which will hold Employee list.
			JSONArray empJsonArray = new JSONArray();
			JSONObject Employee1 = new JSONObject();
			Employee1.put("name", "Ram");
			Employee1.put("surname", "kumar");

			empJsonArray.put(Employee1);
			JSONObject Employee2 = new JSONObject();
			Employee2.put("name", "Varun");
			Employee2.put("surname", "Kumar");
			empJsonArray.put(Employee2);
			employeeJson.put("EmployeeList", empJsonArray);

			System.out.println("Employee JSON" + employeeJson.toString());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Set the content type JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// Set JSON in String
		response.getWriter().write(employeeJson.toString());

	}

}