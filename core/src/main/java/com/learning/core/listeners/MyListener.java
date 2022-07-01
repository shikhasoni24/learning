package com.learning.core.listeners;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.osgi.service.event.EventConstants;


@Component(service = EventHandler.class, property = {
		EventConstants.EVENT_TOPIC + "=" + org.apache.sling.api.SlingConstants.TOPIC_RESOURCE_ADDED,
		EventConstants.EVENT_FILTER + "=(&(path=/content/learning/*)(userid=admin))"})
public class MyListener implements EventHandler {

	
	@Reference
	ResourceResolverFactory factory;
	
	@Override
	public void handleEvent(Event event) {
		
		
		Map<String,Object> map=new HashMap<>();
		map.put(ResourceResolverFactory.SUBSERVICE, "myUser");
		
		try {
			ResourceResolver resolver=factory.getServiceResourceResolver(map);
			String path=event.getProperty("path").toString();
			if(null!=path && path.endsWith("/jcr:content")){
				Resource jcrContent=resolver.getResource(path);
				
				if(null !=jcrContent){
					
					Node jcrNode=jcrContent.adaptTo(Node.class);
					Calendar cal=Calendar.getInstance();
					cal.add(Calendar.DAY_OF_MONTH, -3);
					jcrNode.setProperty("expDate", cal);
					jcrNode.getSession().save();
					
				}
			}
		} catch (LoginException | RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
