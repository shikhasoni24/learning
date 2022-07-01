package com.learning.core.schedulers;



import com.adobe.granite.workflow.WorkflowException;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.Workflow;
import com.learning.core.schedulers.EmailService;

import com.learning.core.schedulers.WorkflowStatusConfiguration;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
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

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.learning.core.constants.AppConstants.EQUALS;
import static com.learning.core.constants.AppConstants.NEW_LINE;
//import static org.redquark.aem.tutorials.core.schedulers.WorkflowStatusScheduler.NAME;

//@Component(name = "Workflow Status Scheduler", service = Runnable.class, immediate = true)
//@Designate(ocd = WorkflowStatusConfiguration.class)
public class WorkflowStatusScheduler implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(WorkflowStatusScheduler.class);
	private static final String TAG = WorkflowStatusScheduler.class.getSimpleName();

	@Reference
	Scheduler scheduler;

	@Reference
	ResourceResolverFactory ref;

	ResourceResolver resourceResolver;
	@Reference
	EmailService emailService;

	private String schedulerName;
	private String toEmail;
	private String ccEmail;
	private String fromEmail;
	private String subject;

	@Activate
	protected void activate(WorkflowStatusConfiguration configuration) {
		LOG.info("msg");
		LOG.info("{}: initializing properties for scheduler", TAG);
		this.schedulerName = configuration.schedulerName();
		LOG.info("{}: name of the scheduler: {}", TAG, schedulerName);

		this.toEmail = configuration.toEmail();
		this.ccEmail = configuration.ccEmail();
		this.fromEmail = configuration.fromEmail();
		this.subject = configuration.subject();
		addScheduler(configuration);
	}

	@Modified
	protected void modified(WorkflowStatusConfiguration configuration) {
		LOG.info("{}: modifying scheduler with name: {}", TAG, schedulerName);
		// Remove the scheduler registered with old configuration
		removeScheduler(configuration);
		// Add the scheduler registered with new configuration
		addScheduler(configuration);
	}

	@Deactivate
	protected void deactivate(WorkflowStatusConfiguration configuration) {
		LOG.info("{}: removing scheduler: {}", TAG, schedulerName);
		removeScheduler(configuration);
	}

	private void addScheduler(WorkflowStatusConfiguration configuration) {
		// Check if the scheduler has enable flag set to true
		if (configuration.enabled()) {
			LOG.info("{}: scheduler: {} is enabled", TAG, schedulerName);
			// Configure the scheduler to use cron expression and some other
			// properties
			ScheduleOptions scheduleOptions = scheduler.EXPR(configuration.cronExpression());
			scheduleOptions.name(schedulerName);
			scheduleOptions.canRunConcurrently(false);
			// Scheduling the job
			scheduler.schedule(this, scheduleOptions);
			LOG.info("{}: scheduler {} is added", TAG, schedulerName);
		} else {
			LOG.info("{}: scheduler {} is disabled", TAG, schedulerName);
			removeScheduler(configuration);
		}
	}

	private void removeScheduler(WorkflowStatusConfiguration configuration) {
		LOG.info("{}: removing scheduler {}", TAG, schedulerName);
		scheduler.unschedule(configuration.schedulerName());
	}

	private String getWorkflowStatus() {
		// This string will store the status for all workflows and other data
		StringBuilder workflowDetails = new StringBuilder();
		Map<String, Object> params = new HashMap<>();
		params.put(ResourceResolverFactory.SUBSERVICE, "myUser");
		try {

			resourceResolver = ref.getServiceResourceResolver(params);
			LOG.info(" resourceResolver is " + resourceResolver);

			// Get the JCR session
			Session session = resourceResolver.adaptTo(Session.class);
			//LOG.info(" session is " + session.getUserID());

			// Get the workflow session
			WorkflowSession wf=resourceResolver.adaptTo(WorkflowSession.class);//granite workflowsession
			

///WorkflowSession workflowSession = workflowService.getWorkflowSession(session);//taking cq session
//   LOG.info(" workflowSession is " + workflowSession.getUser()); // output is MyUser
//cq workflowsession is giving myuser from workflowSession.getUser() and workflowSession.getWorkflows  length is coming as 0
//granite workflowsession does not have any method to get userid of session and  workflowSession.getWorkflows printing 0 size
//but in workflow if we are taking granite wfsession argument from execute method we are getting all the worflows from that session then code is working fine.

			String[] states = {"RUNNING", "COMPLETED"};
			// Get the list of all the workflows by states
			//LOG.info("granite length" +workflowSession.getAllWorkflows().length);// output 0

			
			Workflow[] workflows =  wf.getWorkflows(states);
			LOG.info(" length " + workflows.length); //output 0

			LOG.info(" here is the workflows details " + workflows);
			// Loop through all the workflows

			for (Workflow workflow : workflows) {
				LOG.info("workflow is " + workflow.toString());
				workflowDetails.append("ID: ").append(workflow.getId()).append(NEW_LINE).append("Payload: ")
						.append(workflow.getWorkflowData().getPayload()).append(NEW_LINE).append("State: ")
						.append(workflow.getState()).append(NEW_LINE);
				LOG.info(workflow.getId());
				LOG.info(workflow.getWorkflowData().getPayload().toString());
				LOG.info(workflow.getState());
				LOG.info(workflow.getId());
				LOG.info(workflow.getId());
			}

		} catch (WorkflowException | LoginException e) {
			LOG.error("{}: exception occurred: {}", TAG, e.getMessage());
		}
		return workflowDetails.toString();
	}

	@Override
	public void run() {
		// Getting the workflow status
		String workflowStatus = getWorkflowStatus();
		// Make the content ready
		String content = "Hi, " + NEW_LINE + "Following is the workflow status at: " + LocalDateTime.now() + NEW_LINE
				+ workflowStatus;
		// Send emails
		emailService.sendEmail(toEmail, ccEmail, fromEmail, subject, content);
		LOG.info("{}: workflow status email is sent", TAG);
	}
}
