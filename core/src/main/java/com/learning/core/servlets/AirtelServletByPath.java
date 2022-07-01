package com.learning.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;


@Component(service=Servlet.class, property={
        Constants.SERVICE_DESCRIPTION + "=Airtel Servlet registered by Path",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=" + "/bin/byairtelservlet"
        //or any specific path
        //if we are registering the servlet by path then below extension and selectors will be simply ignored.
})
public class AirtelServletByPath extends SlingSafeMethodsServlet {
	 @Override
	    protected void doGet(final SlingHttpServletRequest req,
	            final SlingHttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/plain");
		 response.getWriter().write("Servlet called by path");

}
}

