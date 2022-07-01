package com.learning.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables=SlingHttpServletRequest.class,defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class ArrayRotationModel {
	
	@ValueMapValue
	String numberinput;
	
	@ValueMapValue
	String iteration;
	
	
	public String output;


	public String getOutput() {
		return output;
	}


	public void setOutput(String output) {
		this.output = output;
	}
	
	@PostConstruct
	public void init() {
		
		
	}
	
	
	

}
