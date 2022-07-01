package com.learning.core.servlets;

import javax.servlet.Servlet;

import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import com.learning.core.models.DialogValueTest;


@Component(service=Servlet.class)
public class DialogValueTestServlet extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DialogValueTest dialogValueTest;
	

}
