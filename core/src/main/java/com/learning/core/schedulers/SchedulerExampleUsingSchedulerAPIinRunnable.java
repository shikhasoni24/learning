package com.learning.core.schedulers;


import org.apache.sling.commons.scheduler.ScheduleOptions;

import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

///@Component(service = Runnable.class, immediate=true)
//@Designate(ocd = AEMGeeksSchedulerConfiguration.class)
public class SchedulerExampleUsingSchedulerAPIinRunnable implements Runnable {

	private static Logger LOG = LoggerFactory.getLogger(SchedulerExampleUsingSchedulerAPIinRunnable.class);

	private int schedulerId;

	@Reference
	Scheduler scheduler;// to schedule or execute your scheduler

	@Activate
	
	protected void activate(AEMGeeksSchedulerConfiguration config) {
		LOG.info("inside activate");
		schedulerId = config.schedulerName().hashCode();
		LOG.info("schedulerId"  +schedulerId);
		//addScheduler(config);
	}
	@Modified
    protected void modified(AEMGeeksSchedulerConfiguration config) {
	LOG.info("inside modified");
	schedulerId = config.schedulerName().hashCode();
	addScheduler(config);
}


	@Deactivate
	protected void deactivate(AEMGeeksSchedulerConfiguration config) {
		removeScheduler();
		LOG.info("inside deactivate");


    }

	private void removeScheduler() {
		scheduler.unschedule(String.valueOf(schedulerId));
	}

	private void addScheduler(AEMGeeksSchedulerConfiguration config) {
		LOG.info("inside addScheduler");

		ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
		scheduleOptions.name(String.valueOf(schedulerId));
		scheduleOptions.canRunConcurrently(false);
		scheduler.schedule(this,scheduleOptions);
		LOG.info("\n----------------scheduler added------------");
	//	ScheduleOptions scheduleOptionsNow=scheduler.NOW(2,5);
		/*now(3,5) means run method will execute as soon  as the bundle is deployed and 
		 2 more times at an interval of 5 seconds*/
		/*As soon as this bundle will be deployed 
	     run method will execute once (if now() is there) after that it will run on the basis of cron expression*/
		
	//	scheduler.schedule(this, scheduleOptionsNow);

	}

	@Override
	public void run() {
		LOG.info("inside SchedulerExampleUsingSchedulerAPIinRunnable scheduler");
	}

}
