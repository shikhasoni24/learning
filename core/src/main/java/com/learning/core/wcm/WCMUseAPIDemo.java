package com.learning.core.wcm;

import java.util.HashMap;


import java.util.Map;
import java.util.Map.Entry;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;

public class WCMUseAPIDemo extends WCMUsePojo {

	private String currentNodeProperty;
	private Map<String, Object> currentResourceProperties = new HashMap<String, Object>();
	private String otherResourceProperty;

	private String currentPageTitle;
	private String differentPageTitle;
	private String otherResourcePropertyJCR;

	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub

		// Fetch single property of current resource / component / node
		currentNodeProperty = getProperties().get("text", String.class).toUpperCase();
		currentNodeProperty = getProperties().get("sling:resourceType", String.class);

		// Second way to fetch single property
		ValueMap prop = getResource().adaptTo(ValueMap.class);
		//ValueMap prop1=getRequest().adaptTo(ValueMap.class);
		currentNodeProperty += prop.get("text", String.class);
		

		// Third way to fetch single property

		currentNodeProperty += getResource().getValueMap().get("jcr:created", String.class);

		// Fetch all the properties of component
		ValueMap properties = getResource().adaptTo(ValueMap.class);
		for (Entry<String, Object> entry : properties.entrySet()) {
			currentResourceProperties.put(entry.getKey(), entry.getValue());
		}
		// Access Different Resource
		ResourceResolver resourceResolver = getResourceResolver();
		Resource resource = resourceResolver
				.getResource("/content/learning/en/demo/jcr:content/root/responsivegrid/text");

		// First way
		otherResourceProperty = resource.getValueMap().get("xyz", String.class);

		// Page API
		currentPageTitle = getCurrentPage().getTitle().toString();

		// Page API Different Page title
		Resource res = resourceResolver.getResource("/content/learning/en/demo.html");
		Page page = res.adaptTo(Page.class);
		differentPageTitle = page.getTitle();

		// JCR :: Node, Session
		Session session = getResourceResolver().adaptTo(Session.class);
		Node node = session.getNode("/content/learning/en/demo/jcr:content/root/responsivegrid/text");
		otherResourcePropertyJCR = node.getProperty("xyz").getString();
		node.setProperty("xyz", "override it");
		// JCR :: Addition of Node
		//node.addNode("abc");
		session.save();
		
	}

	public String getCurrentNodeProperty() {
		return currentNodeProperty;
	}

	

	public Map<String, Object> getCurrentResourceProperties() {
		return currentResourceProperties;
	}
	public String getOtherResourceProperty() {
		return otherResourceProperty;
	}

	public String getDifferentPageTitle() {
		return differentPageTitle;
	}

	public String getCurrentPageTitle() {
		return currentPageTitle;
	}

	
	public String getOtherResourcePropertyJCR() {
		return otherResourcePropertyJCR;
	}

}
