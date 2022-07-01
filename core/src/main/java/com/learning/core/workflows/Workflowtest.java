package com.learning.core.workflows;
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
import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

//@Component(service = WorkflowProcess.class, property = { "process.label=adding a node Step in wf" })
public class Workflowtest implements WorkflowProcess {
@Reference
	ResourceResolverFactory ref;
	ResourceResolver resourceResolver = null;
@Override
		public void execute(WorkItem arg0, WorkflowSession arg1, MetaDataMap arg2) throws WorkflowException {
		//	arg1.get
			WorkflowData workflowdData = arg0.getWorkflowData();
			Workflow Workflow=arg0.getWorkflow();
			String payLoad = workflowdData.getPayload().toString();

			Resource resource = null;
			Map<String, Object> params = new HashMap<>();
			params.put(ResourceResolverFactory.SUBSERVICE, "myUser");
			Node assetNode = null;
			Session session = null;
			try {
resourceResolver = ref.getServiceResourceResolver(params);
				 session = resourceResolver.adaptTo(Session.class);	
				resource = resourceResolver.getResource(payLoad);
		//		logger.info("payload has been adapted as resource");
				if (resource != null) {
			        assetNode = resource.adaptTo(Node.class);
				//	assetNode = resourceResolver.getResource(payLoad).adaptTo(Node.class);
				}
				//if(assetNode.hasNode("sampleNode"))
				Node sampleNode = assetNode.addNode("newsampleNode", "nt:unstructured");
			//	logger.info("sampleNode data is", sampleNode);

			//	if (sampleNode.hasProperty("sampleProp")) {
					sampleNode.setProperty("newsampleProp", "Sample Value");
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
				}}}}
