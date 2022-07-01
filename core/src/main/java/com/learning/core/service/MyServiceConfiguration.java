package com.learning.core.service;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

//My service Configuration interface

@ObjectClassDefinition(name="My service Configuration" , description="Service Configuration")
public @interface MyServiceConfiguration {
	 
	@AttributeDefinition(name="Config Value", description="Configuration value")
	String configValue();
	
	@AttributeDefinition(name="Multiple Values", description="Multiple values")
    String[] StringValues();
	
	@AttributeDefinition(name="Number Value", description="Number value", type=AttributeType.INTEGER)
    int NumberValue();
	
}
