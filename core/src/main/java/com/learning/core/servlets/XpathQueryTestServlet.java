package com.learning.core.servlets;

import java.io.IOException;


import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import com.learning.core.serviceimpl.XpathQueryTestServiceImpl;

@Component(service = Servlet.class, immediate = true, property = { "sling.servlet.paths=/bin/search/xpathquery" })
public class XpathQueryTestServlet extends SlingSafeMethodsServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Reference
	XpathQueryTestServiceImpl xpathQueryTest;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		try {
			JSONObject jsonObj1 = xpathQueryTest.getParticularDateQuery();
			response.getWriter().write(jsonObj1.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
