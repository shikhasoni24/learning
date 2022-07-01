package com.learning.core.models;

import javax.annotation.PostConstruct;


import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Source;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;

@Model(adaptables=SlingHttpServletRequest.class,adapters=AirtelModelInterface.class)
//@Exporter(name = "jackson", extensions = "json", selector="yoyo",options = {
//		@ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true"),
//		@ExporterOption(name = "SerializationFeature.WRITE_NULL_MAP_VALUES", value = "false") })
//@JsonIgnoreProperties(value = { "title"})
public class AirtelModel implements AirtelModelInterface{

	
	String str="shikha soni";
	String zzz="any string";
	
	  
	public String getZzz() {
		return zzz;
	}


	public void setZzz(String zzz) {
		this.zzz = zzz;
	}

	@Inject()
	   @Named(value="jcr:title")
	   @Optional
	   @Via("resource")
	   private String title;
	   
	   
	   @ValueMapValue(via="resource",injectionStrategy=InjectionStrategy.OPTIONAL)
	   private String description;
	   
	   
	   @ScriptVariable
	   Page currentPage;
	   @Source(value = " ")
	   @SlingObject
	   private ResourceResolver resourceResolver;
	   
	   @Self
	   Resource resource;
	   
	   
	   @PostConstruct
	   public void init(){
		   title=currentPage.getTitle();
		   resource=resourceResolver.getResource("");
		   Page page=resource.adaptTo(Page.class);
		   title +="Different Page Title" + page.getTitle();
		   // Call external Services
	   }
	   
	   
	@Override
	public String getTitle() {
		return title.toUpperCase().toString();
	}

	@Override
	public String getDescription() {
		return description;
	}

}
