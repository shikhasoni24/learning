package com.learning.core.schedulers;

import java.util.List;


public interface JobFactory {
	public List<JobFactory> getTotalConfigsList();

	public String getCronExpression();

	public String getCountryName();

	public String getServiceURL();
}
