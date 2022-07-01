package com.learning.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "A scheduled task for unpublish", description = "Simple demo for cron-job like task with properties")
public @interface UnpublishConfig {

	@AttributeDefinition(name = "Cron-job expression")
	String scheduler_expression() default "0 * * ? * *";

	@AttributeDefinition(name = "A parameter", description = "Can be configured in /system/console/configMgr")
	String myParameter() default "it is a parameter";

}
