package com.learning.core.models;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.learning.core.service.FactoryConfigurationDemoService;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FactoryConfigurationDemoServiceModel {

	@OSGiService
	FactoryConfigurationDemoService factoryConfigurationDemoService;

	public List<FactoryConfigurationDemoService> getAllConfigsList() {

		return factoryConfigurationDemoService.getConfigsList();
	}
}