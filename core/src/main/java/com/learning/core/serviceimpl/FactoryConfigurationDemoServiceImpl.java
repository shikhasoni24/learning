package com.learning.core.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.learning.core.service.FactoryConfiguration;
import com.learning.core.service.FactoryConfigurationDemoService;

//@Component(service = FactoryConfigurationDemoService.class,immediate = true, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = FactoryConfiguration.class, factory=true)
// factory = true will make the configuration as factory
public class FactoryConfigurationDemoServiceImpl implements FactoryConfigurationDemoService {

	private static final Logger LOG = LoggerFactory.getLogger(FactoryConfigurationDemoServiceImpl.class);

	private int configId;
	private String serviceName;
	private String serviceURL;

	private List<FactoryConfigurationDemoService> configsList;

	@Activate
	@Modified
	protected void activate(final FactoryConfiguration config) {
		LOG.info("in activate");
		configId = config.configId();
		serviceName = config.serviceName();
		serviceURL = config.serviceURL();
	
	}

	@Reference(service = FactoryConfigurationDemoService.class, cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	// cardinality = ReferenceCardinality.MULTIPLE ->each time we will add a config it will listen to this method
	//policy = ReferencePolicy.DYNAMIC -> dynamic means it will search for unbind whenever we will remove a config
	protected synchronized  void bindFactoryConfigurationDemoService(final FactoryConfigurationDemoService config) {
		if (configsList==null) {
			configsList = new ArrayList<>();
		}
		configsList.add(config);
		LOG.info("config added");
	
		configsList.forEach(i->LOG.info(i.getServiceName()));
		configsList.forEach(i->LOG.info(i.getServiceURL()));
		
	}

	protected synchronized  void unbindFactoryConfigurationDemoService(final FactoryConfigurationDemoService config) {
		configsList.remove(config);
		LOG.info("config removed");
	}

	@Override
	public int getConfigId() {
		return configId;
	}

	@Override
	public String getServiceName() {
		return serviceName;
	}

	@Override
	public String getServiceURL() {
		return serviceURL;
	}

	@Override
	public List<FactoryConfigurationDemoService> getConfigsList() {
		return configsList;
		
	}

}
