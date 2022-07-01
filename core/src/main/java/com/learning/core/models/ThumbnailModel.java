package com.learning.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.factory.ModelFactory;

import com.day.cq.wcm.api.Page;

@Model(adaptables=HttpServletRequest.class)
public class ThumbnailModel {
	
	@ScriptVariable
	Page currentPage;
	@SlingObject
	ResourceResolver resolver;
@Inject
ModelFactory fac;
	private String image;
	
	
	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	@PostConstruct
	protected void init(){
		
		String p=currentPage.getPath()+"/jcr:content";
		Resource r=resolver.getResource(p);
		if(r!= null && r.getChild("image") != null) {
			if (r.getChild("image").getChild("file") != null) {
				image = r.getChild("image").getChild("file").getPath();
				
			}
			else{
				
			image=r.getChild("image").getValueMap().get("fileReference").toString();
			}
    	}
		
		
	}

}
