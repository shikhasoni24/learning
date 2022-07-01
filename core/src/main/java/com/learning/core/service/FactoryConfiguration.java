package com.learning.core.service;

import org.osgi.service.metatype.annotations.AttributeDefinition;

import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="AEM Geeks OSGI Factory Configuration", description="Osgi factory configuration demo")
public @interface FactoryConfiguration {
	
	@AttributeDefinition(name="Config id", description="Enter service id", type=AttributeType.INTEGER)
	int configId() default 1;
	
	@AttributeDefinition(name="Service name", description="Enter service name", type=AttributeType.STRING)
	public String serviceName() default "Service #";
	
	@AttributeDefinition(name="Service URL", description="Add Service URL", type=AttributeType.STRING)
	public String serviceURL() default "URL #";

}
