package com.learning.core.service;

import java.util.List;

public interface FactoryConfigurationDemoService {

	public List<FactoryConfigurationDemoService> getConfigsList();

	public String getServiceName();

	public int getConfigId();

	public String getServiceURL();

}
