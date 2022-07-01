package com.learning.core.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.resolver.Resolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.QueryBuilder;
import com.day.cq.wcm.api.PageManager;

@Component(service = Servlet.class, immediate = true, property = {
		Constants.SERVICE_DESCRIPTION + "=Servlet to find unique components",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, // bydefault always
																// get but if we
																// have
																// mentioned
																// post here
		// then we have to explicitly mention get also if we waat both to
		// execute.
		"sling.servlet.paths=/bin/findcomponents", "sling.servlet.selectors=uniqueselector",
		"sling.servlet.extensions=json" })
public class UniqueComponents extends SlingSafeMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger LOG = LoggerFactory.getLogger(UniqueComponents.class);

	@Reference
	private ResourceResolverFactory factory;

	@Reference
	private QueryBuilder queryBuilder;

	@Reference
	private SlingRepository repository;

	protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException {

		Set<String> set = new HashSet<String>();
		String queryString = req.getParameter("res");
		ResourceResolver resourceResolver = req.getResourceResolver();
		try {
			Iterator<Resource> result = resourceResolver
					.findResources("SELECT [sling:resourceType] FROM [nt:base] AS s WHERE ISDESCENDANTNODE(["
							+ queryString + "]) and [sling:resourceType] like '%%'", Query.JCR_SQL2);
			while (result.hasNext()) {
				Resource componentResource = result.next();
				set.add(componentResource.getResourceType());
			}

		} finally {
			System.out.println("I am cool");
		}

		resp.setContentType("text/html");
		resp.getWriter().write("<b>Below components are used = </b><br /><br />");
		for (String s : set) {
			resp.getWriter().write(s + "<br />");
		}
	}

}
