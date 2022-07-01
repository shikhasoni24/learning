//package com.learning.core.schedulers;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.osgi.service.component.annotations.Activate;
//import org.osgi.service.component.annotations.Component;
//import org.osgi.service.component.annotations.ConfigurationPolicy;
//import org.osgi.service.component.annotations.Modified;
//import org.osgi.service.component.annotations.Reference;
//import org.osgi.service.component.annotations.ReferenceCardinality;
//import org.osgi.service.component.annotations.ReferencePolicy;
//import org.osgi.service.metatype.annotations.Designate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Component(service = JobFactory.class, immediate = true)
//@Designate(ocd = AEMGeeksSchedulerConfiguration.class, factory = true)
//// factory = true will make the configuration as factory
//public class JobFactoryimpl implements JobFactory {
//
//	private static final Logger LOG = LoggerFactory.getLogger(JobFactoryimpl.class);
//
//	private String cronExpression;
//	private String countryName;
//	private String serviceURL;
//
//	private List<JobFactory> configsList;
//	
//	@Reference
//	SchedulerExampleUsingSchedulerAPIinJob schedulerExampleUsingSchedulerAPIinJob;
//	
//	@Activate
//	@Modified
//	protected void activate(final AEMGeeksSchedulerConfiguration config) {
//		LOG.info("in activate");
//		cronExpression = config.cronExpression();
//		countryName = config.countryName();
//		serviceURL = config.serviceURL();
//
//	}
//
//	@Reference(service = JobFactory.class, cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
//	// cardinality = ReferenceCardinality.MULTIPLE ->each time we will add a
//	// config it will listen to this method
//	// policy = ReferencePolicy.DYNAMIC -> dynamic means it will search for
//	// unbind whenever we will remove a config
//	protected synchronized void bindJobFactory(final JobFactory config) {
//		if (configsList == null) {
//			configsList = new ArrayList<>();
//		}
//		configsList.add(config);
//		LOG.info("config added");
//
//		configsList.forEach(i -> LOG.info(i.getCountryName()));
//		configsList.forEach(i -> LOG.info(i.getServiceURL()));
//		schedulerExampleUsingSchedulerAPIinJob.addSchedulerJob();
//	}
//
//	protected synchronized void unbindJobFactory(final JobFactory config) {
//		configsList.remove(config);
//		LOG.info("config removed");
//	}
//
//	@Override
//	public String getCountryName() {
//		return countryName;
//	}
//
//	@Override
//	public String getServiceURL() {
//		return serviceURL;
//	}
//
//	@Override
//	public List<JobFactory> getTotalConfigsList() {
//		return configsList;
//
//	}
//
//	@Override
//	public String getCronExpression() {
//		// TODO Auto-generated method stub
//		return cronExpression;
//	}
//
//}
