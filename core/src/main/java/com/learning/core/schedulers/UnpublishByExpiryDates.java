 package com.learning.core.schedulers;

import java.util.Calendar;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.learning.core.schedulers.SimpleScheduledTask.Config;

@Designate(ocd=UnpublishConfig.class)
@Component(service = Runnable.class,immediate=true)
//we can schedule multiple jobs in one class using either runnable or job interface.(both will use scheduler API)
public class UnpublishByExpiryDates implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(UnpublishByExpiryDates.class);
	private static final String ROOT_PAGE = "/content/learning/en/vv";

	@Reference
	ResourceResolverFactory ref;

	@Reference
	private Replicator replicator;
	// we need to inject this service if we want to activate and deactivate a page

	 private String myParameter;

    @Activate
    protected void activate(final UnpublishConfig config) {
        myParameter = config.myParameter();
    }
    
	@Override
	public void run() {
		LOG.debug("UnpublishByExpiryDate scheduler",myParameter);

		Map<String, Object> prop = new HashMap<String, Object>();
		prop.put(ResourceResolverFactory.SUBSERVICE, "myUser");
		try {
			ResourceResolver resolver = ref.getServiceResourceResolver(prop);
			// Resource resource=resolver.getResource(ROOT_PAGE);
			// Page page = resource.adaptTo(Page.class);

			PageManager pageManager = resolver.adaptTo(PageManager.class);
			Page rootPage = pageManager.getPage(ROOT_PAGE);
			LOG.info("rootpage path is" + rootPage.getPath());

			Iterator<Page> childPages = rootPage.listChildren();
			while (childPages.hasNext()) {
				Page page = childPages.next();
				LOG.info("page path is" + page.getPath());
				if (null != page) {
					//String replicationStatus =page.getProperties("cq:lastReplicationAction").toString();
					String replicationStatus = page.getProperties().get("cq:lastReplicationAction", String.class);
					LOG.info("cq:lastReplicationAction is " + replicationStatus);
					Calendar expDate = page.getProperties().get("expDate", Calendar.class);
					LOG.info("expDate is " + expDate);

					Date currentDate = new Date();
					if (expDate != null && replicationStatus!=null ) {
						if (currentDate.compareTo(expDate.getTime()) >= 0
								&& replicationStatus.equalsIgnoreCase("Activate")) {

							replicator.replicate(resolver.adaptTo(Session.class), ReplicationActionType.DEACTIVATE,
									page.getPath());

						}
					}
				}
			}

		} catch (LoginException | ReplicationException e) {
			e.printStackTrace();
		}
	}
}
