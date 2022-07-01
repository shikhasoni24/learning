package com.learning.core.serviceimpl;

import java.util.Map;


import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.learning.core.service.KeyDocumentService;
import com.learning.core.service.MathService;
import com.learning.core.service.MySimpleService;

@Component(service=MathService.class,name = "Math Service", immediate = true)
@Properties({
		@Property(name = "addition.first", label = "Enterfirst value", description = "First Value", intValue = 30),
	@Property(name = "addition.second", label = "Enterfirst value", description = "Second Value", intValue = 30),

})
// immediate = true means, it automatically start the service
// label will appear in the name of the bundle in OSGI console

public class MathServiceImpl implements MathService {
	



	int firstValue;
	int secondValue;
	
	@Activate
	public final void activate(final Map<String,Object> properties){  // this map will contain list of all the properties line no 16,17
		firstValue=(Integer)properties.get("addition.first");
		secondValue=(Integer)properties.get("addition.second");

	}
	

	@Override
	public int calculateValue() {
		// TODO Auto-generated method stub
		return this.firstValue+this.secondValue;
		//ff.getKeyDocumentDetails(linkPath, request);
	}

}
