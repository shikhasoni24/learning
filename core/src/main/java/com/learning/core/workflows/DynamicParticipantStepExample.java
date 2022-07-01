package com.learning.core.workflows;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.HistoryItem;
import com.adobe.granite.workflow.exec.ParticipantStepChooser;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(immediate = true, property = { "chooser.label=Dynamic Participant Step Example" })
public class DynamicParticipantStepExample implements ParticipantStepChooser {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override

	public String getParticipant(WorkItem workitem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
			throws WorkflowException {
		String participant = "admin";
		Workflow workflow = workitem.getWorkflow();

		// Getting the workflow history
		List<HistoryItem> workflowHistory = workflowSession.getHistory(workflow);

		if (!workflowHistory.isEmpty()) {
			// Setting the administrators group to the participant
			participant = "administrators";
		} else {
			participant = "admin";
		}

		log.info("----------< Participant: {} >----------", participant);

		return participant;
	}

}
