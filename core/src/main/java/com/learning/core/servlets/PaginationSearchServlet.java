package com.learning.core.servlets;

import java.io.IOException;



import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.learning.core.service.PaginationSearch;

@Component(service = Servlet.class, immediate = true, property = { "sling.servlet.paths=/bin/search/paginationsearch" })
public class PaginationSearchServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;
	@Reference
	PaginationSearch paginationSearch;
	

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		try {
			String searchword = request.getParameter("fname");
			int startIndex=0;
		    int limit=20;
			JSONObject jsonObj = paginationSearch.search(searchword,startIndex,limit);
			
			
		//	response.setContentType("application/json");
			response.getWriter().write(jsonObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
