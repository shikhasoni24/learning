package com.learning.core.models;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.learning.core.service.DialogFieldPermissionService;
import com.learning.core.utility.ResourceResolverUtil;

@Model(adaptables = { Resource.class,
		SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DialogFieldPermissionModel {
	
	
	@OSGiService
	ResourceResolverFactory factory;
	
	@OSGiService
	DialogFieldPermissionService dfps;
	
	@SlingObject
	ResourceResolver resolver;
	
	@PostConstruct
	protected void init(){
		Session session=resolver.adaptTo(Session.class);
		String userId=session.getUserID();
		Resource res=resolver.getResource("/apps/shikha/components/content/SignUp/cq:dialog/content/items/column/items/text");
//		String path="/apps/shikha/components/content/SignUp/cq:dialog/content/items/column/items";
		
		if(userId.equalsIgnoreCase("dialogtestuser"))
		{
		dfps.modifyNodePermission(res, userId,session);
		}
		
	}

}
