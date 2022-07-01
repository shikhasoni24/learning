package com.learning.core.workflows;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

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

@Component(service=WorkflowProcess.class,property={"process.label=adding a new node in my workflow"})
public class NewNodeAddition implements WorkflowProcess{

	
	@Reference
	ResourceResolverFactory factory;
	
	
	ResourceResolver resolver = null;
	
	@Override
	public void execute(WorkItem arg0, WorkflowSession arg1, MetaDataMap arg2) throws WorkflowException {
		
		WorkflowData workflowData=arg0.getWorkflowData();
		Workflow workflow=arg0.getWorkflow();
		String payload=workflowData.getPayload().toString();
		
		
		Resource res=null;
		Map<String,Object> params=new HashMap<>();
		params.put(ResourceResolverFactory.SUBSERVICE, "myUser");
		Node assetNode=null;
	//	Session session =null;
		
		try {
			resolver=factory.getServiceResourceResolver(params);
			//session=resolver.adaptTo(Session.class);
			res=resolver.getResource(payload);
			if(res!=null){
				
				assetNode=res.adaptTo(Node.class);
				Node sampleNode=assetNode.addNode("aemdemonode","nt:unstructured");
				sampleNode.setProperty("aemdemoproperty", "sample value");
				assetNode.getSession().save();
				//session.save();
			}
			
			
		} catch (LoginException | RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
