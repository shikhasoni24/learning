package com.learning.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;

import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "AEM Geeks Scheduler configuration", description = "sling scheduler Configuration")
public @interface AEMGeeksSchedulerConfiguration {

	@AttributeDefinition(name = "Scheduler name", description = "Name of the scheduler", type = AttributeType.STRING)
	public String schedulerName() default "Custom sling scheduler Configuration";

	@AttributeDefinition(name = "Cron Expression", description = "Cron Expression used by the scheduler", 
			type = AttributeType.STRING)
	public String cronExpression() default "*/20 * * * * ?";//runs every 10 seconds
	
	@AttributeDefinition(name = "country name", description = "Name of the country", type = AttributeType.STRING)
	public String countryName() default "india";

	@AttributeDefinition(name = "url", description = "url", type = AttributeType.STRING)
	public String serviceURL() default "www.in.com";
	
	
			//*/20 * * * * ?
	//0 33 16 1/1 * ? *

}
