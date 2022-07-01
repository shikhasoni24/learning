package com.learning.core.workflows;

import static com.learning.core.constants.AppConstants.NEW_LINE;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.learning.core.service.WeatherService;

@Component(property = { "process.label=adding a node Step" })
public class PopulateNewNode implements WorkflowProcess {


	@Reference
	ResourceResolverFactory ref;
	
	
	@Reference
	WeatherService w;
	
	private static final Logger logger = LoggerFactory.getLogger(PopulateNewNode.class);

	
	ResourceResolver resourceResolver = null;
	
	

	@Override
	public void execute(WorkItem arg0, WorkflowSession arg1, MetaDataMap arg2) throws WorkflowException {
		// TODO Auto-generated method stub
	//	arg1.get
		WorkflowData workflowdData = arg0.getWorkflowData();
		Workflow Workflow=arg0.getWorkflow();
		
		String initiator=Workflow.getInitiator();
		logger.info("workflow initiator is"  +initiator);


	//	logger.info("workflow data is", workflowdData);

		String payLoad = workflowdData.getPayload().toString();
	//	logger.info("payload data is", payLoad);

		Resource resource = null;
		Map<String, Object> params = new HashMap<>();
		params.put(ResourceResolverFactory.SUBSERVICE, "myUser");
		Node assetNode = null;
		Session session = null;
		StringBuilder workflowDetails = new StringBuilder();

		try {
			
			// to take res resolver, we want a service user, in servlet we get res resolver by request object
			resourceResolver = ref.getServiceResourceResolver(params);
			 session = resourceResolver.adaptTo(Session.class);
			 logger.info("line 62");
			 String[] states = { "RUNNING", "COMPLETED" };
				// Get the list of all the workflows by states
			 

				Workflow[] workflows= arg1.getWorkflows(states);
			//	workflows
				logger.info(" length " + workflows.length);

				for (Workflow workflow : workflows) {
					logger.info("workflow is " + workflow.toString());
					workflowDetails.append("ID: ").append(workflow.getId()).append(NEW_LINE).append("Payload: ")
							.append(workflow.getWorkflowData().getPayload()).append(NEW_LINE).append("State: ")
							.append(workflow.getState()).append(NEW_LINE);
					logger.info("line no 72"  +workflow.getId());
					logger.info(workflow.getWorkflowData().getPayload().toString());
					logger.info(workflow.getState());
					logger.info(workflow.getId());
				}
		//	logger.info("Asset node path is", payLoad);
			resource = resourceResolver.getResource(payLoad);
	//		logger.info("payload has been adapted as resource");
			if (resource != null) {
		        assetNode = resource.adaptTo(Node.class);
			//	assetNode = resourceResolver.getResource(payLoad).adaptTo(Node.class);

			}
			// if(assetNode.hasNode("sampleNode"))
			Node sampleNode = assetNode.addNode("sampleNode", "nt:unstructured");
		//	logger.info("sampleNode data is", sampleNode);

		//	if (sampleNode.hasProperty("sampleProp")) {
				sampleNode.setProperty("sampleProp", "Sample Value");
			//}

			assetNode.getSession().save();
			//session.save();
		} catch (LoginException e) {
			e.getMessage();

		} catch (RepositoryException e) {
			e.getMessage();
		} finally {
			if (session != null && session.isLive()) {
				session.logout();
			}
			if (resourceResolver != null && resourceResolver.isLive()) {
				resourceResolver.close();
			}

		}
	}

	
}
