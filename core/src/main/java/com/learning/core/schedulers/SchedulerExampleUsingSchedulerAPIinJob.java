
package com.learning.core.schedulers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.commons.scheduler.Job;
import org.apache.sling.commons.scheduler.JobContext;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component(service = {Job.class,JobFactory.class} ,immediate = true,configurationPolicy=ConfigurationPolicy.REQUIRE)
@Designate(ocd = AEMGeeksSchedulerConfiguration.class, factory=true)
public class SchedulerExampleUsingSchedulerAPIinJob implements Job,JobFactory {

	private static Logger LOG = LoggerFactory.getLogger(SchedulerExampleUsingSchedulerAPIinJob.class);


	@Reference
	Scheduler scheduler;
	
	private String cronExpression;
	private String countryName;
	private String serviceURL;

	private List<JobFactory> configsList;
		
	@Activate
	@Modified
	protected void activate(final AEMGeeksSchedulerConfiguration config) {
		LOG.info("in activate");
		cronExpression = config.cronExpression();
		countryName = config.countryName();
		serviceURL = config.serviceURL();

	}
	@Reference(service = JobFactory.class, cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
public void bindJobFactory(final JobFactory config) {
		LOG.info("in bind method");
		if (configsList == null) {
			configsList = new ArrayList<>();
		}
		configsList.add(config);
		LOG.info("config added");

	//	configsList.forEach(i -> LOG.info(i.getCountryName()));
	//	configsList.forEach(i -> LOG.info(i.getServiceURL()));
		addSchedulerJob();
	}

	public void unbindJobFactory(final JobFactory config) {
		configsList.remove(config);
		//LOG.info("config removed");
	}

//scheduler API  to schedule or execute your scheduler

//	@Modified
//	protected void activate(FactoryConfiguration config) {
//		//schedulerId = config.schedulerName().hashCode();
//		addSchedulerJob();
//	}
//
//	@Deactivate
//	protected void deactivate(FactoryConfiguration config) {
//		scheduler.unschedule(String.valueOf(schedulerId));
//    //removeScheduler();
//	}
//
//	private void removeScheduler() {
//		scheduler.unschedule(String.valueOf(schedulerId));
//		
//	}

	public void addSchedulerJob() {
		LOG.info("in addSchedulerJob");
		LOG.info("configsList" +configsList);
		for(JobFactory obj: configsList){
			ScheduleOptions in = scheduler.EXPR(obj.getCronExpression());
			Map<String, Serializable> inMap = new HashMap<>();
			inMap.put("country", obj.getCountryName());
			inMap.put("url", obj.getServiceURL());
			in.config(inMap);
			scheduler.schedule(this, in);

		}
		
		
//		ScheduleOptions in = scheduler.EXPR("0 22 08 1/1 * ? *");
//		Map<String, Serializable> inMap = new HashMap<>();
//		inMap.put("country", "in");
//		inMap.put("url", "www.in.com");
//		in.config(inMap);
//		scheduler.schedule(this, in);
//
//		ScheduleOptions de = scheduler.EXPR("0 23 08 1/1 * ? *");
//		Map<String, Serializable> deMap = new HashMap<>();
//		deMap.put("country", "de");
//		deMap.put("url", "www.de.com");
//		de.config(deMap);
//		scheduler.schedule(this, de);
//
//		ScheduleOptions us = scheduler.EXPR("0 24 08 1/1 * ? *");
//		Map<String, Serializable> usMap = new HashMap<>();
		
//		usMap.put("country", "us");
//		usMap.put("url", "www.us.com");
//		us.config(usMap);
//		scheduler.schedule(this, us);
	}

	@Override
	public String toString() {
		return "SchedulerExampleUsingSchedulerAPIinJob [scheduler=" + scheduler + ", cronExpression=" + cronExpression
				+ ", countryName=" + countryName + ", serviceURL=" + serviceURL + ", configsList=" + configsList + "]";
	}
	@Override
	public void execute(JobContext jobContext) {
		/*
		 * this will execute for each job , but how will we pass our job
		 * specific data because this method does Not know which method is
		 * executing and in our case we need to have a different service URL for
		 * different countries, for all the three countries we are hitting the
		 * diferent service, so this method should know.Now for that case
		 * jobcontext comes into picture, u can add job specific custom
		 * variables to this job context for each job and then u can schedule
		 * the execution
		 */

		LOG.info("\n----> COUNTRY : URL {}  : {} ", jobContext.getConfiguration().get("country"),
				jobContext.getConfiguration().get("url"));
	}



	@Override
	public String getCountryName() {
		return countryName;
	}

	@Override
	public String getServiceURL() {
		return serviceURL;
	}

	@Override
	public List<JobFactory> getTotalConfigsList() {
		return configsList;

	}

	@Override
	public String getCronExpression() {
		return cronExpression;
	}

}
