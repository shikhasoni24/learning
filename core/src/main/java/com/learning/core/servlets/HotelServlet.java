package com.learning.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learning.core.dao.UserData;
import com.learning.core.models.DialogValueTest;

@Component(service = Servlet.class, property = { "sling.servlet.paths=/bin/hotel/data"})
// "sling.servlet.resourceTypes=learning/components/structure/page",
// "sling.servlet.selector=customselector",
// "sling.servlet.extensions=html", "sling.servlet.methods=GET" })
public class HotelServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;
	private static Logger LOG = LoggerFactory.getLogger(HotelServlet.class);
	DialogValueTest dialogValueTest;
	@Reference
	UserData userData;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
		try {
			 dialogValueTest=new DialogValueTest();

			String hotelName = request.getParameter("hName");
			dialogValueTest.setVal("dyrtufguogftoukkj");
			String str=dialogValueTest.getVal();
			String hotelState = request.getParameter("hState");
			LOG.info("hotelName is" + hotelName);
			LOG.info("hotelState is" + hotelState);
			String res=userData.setHotelData(hotelName, hotelState);
			
			response.setContentType("text/plain");
			response.getWriter().write(res);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
