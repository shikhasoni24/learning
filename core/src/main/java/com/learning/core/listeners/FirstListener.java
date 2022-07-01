package com.learning.core.listeners;
import java.util.Calendar;


import java.util.HashMap;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component(service = EventHandler.class, property = {
		//EventConstants.EVENT_TOPIC + "=" + org.apache.sling.api.SlingConstants.TOPIC_RESOURCE_ADDED,
	//	EventConstants.EVENT_TOPIC + "=" + org.apache.sling.api.SlingConstants.TOPIC_RESOURCE_CHANGED,
		//EventConstants.EVENT_FILTER + "=(&(path=/content/learning/*)(userid=admin))"})
public class FirstListener implements EventHandler {
private static final Logger LOG = LoggerFactory.getLogger(FirstListener.class);
@Reference
	private ResourceResolverFactory factory;
@Override
	public void handleEvent(Event event) {
		LOG.info("Event Topic is- {}", event.getTopic());

		HashMap<String, Object> p = new HashMap<String, Object>();
		p.put(ResourceResolverFactory.SUBSERVICE, "myUser");
try {
			ResourceResolver resolver = factory.getServiceResourceResolver(p);
			String path = event.getProperty("path").toString();
			LOG.info("path is : " +path);
			if (null != path && path.endsWith("/jcr:content")) {
				LOG.info("path is inside if : " +path);
				Resource jcrContent = resolver.getResource(path);
				LOG.info("path of jcr:content is : " +jcrContent.getPath());
if (null != jcrContent) {
					Node jcrNode = jcrContent.adaptTo(Node.class);
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DAY_OF_MONTH, 3);
					jcrNode.setProperty("expDate", cal);
					jcrNode.getSession().save();
				}
			}
		} catch (LoginException | RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
/*
 * String[] props = event.getPropertyNames(); for(String prop:props) {
 * LOG.info("Event prop name {} - event prop value {}", prop,
 * event.getProperty(prop)); }
 */}}
