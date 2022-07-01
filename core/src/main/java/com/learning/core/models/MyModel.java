package com.learning.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class,defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL,
adapters={Firstinterface.class,Secondinterface.class})
public class MyModel implements Firstinterface,Secondinterface {
/*either we write Firstinterface or Secondinterface or classname in sightly it will print values of all the attributes*/
	
	
	private String description="soni";
	private String title="shikha";
	
	
	@Override
	public String getDescription() {
		return description.toUpperCase().toString();
	}

	@Override
	public String getTitle() {
		return title.toUpperCase().toString();
	}

}
