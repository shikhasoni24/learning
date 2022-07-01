package com.learning.core.schedulers;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.Replicator;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = Runnable.class, property = { "scheduler.expression=0,30 * * ? * * *" })
// expression=0,30 * * ? * * *
public class PublishChildPagesThroughQuery implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(PublishChildPagesThroughQuery.class);

	@Reference
	ResourceResolverFactory ref;

	@Reference
	QueryBuilder builder;

	@Reference
	private Replicator replicator;

	@Override
	public void run() {
		LOG.info("hello PublishChildPagesThroughQuery");
		Map<String, Object> prop = new HashMap<String, Object>();
		prop.put(ResourceResolverFactory.SUBSERVICE, "myUser");
		try {
			ResourceResolver resolver = ref.getServiceResourceResolver(prop);
			builder = resolver.adaptTo(QueryBuilder.class);
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("type", "cq:Page");
			queryMap.put("property", "jcr:content/cq:lastReplicationAction");
			queryMap.put("property.value", "Deactivate");
			queryMap.put("p.limit", "-1");
			Query query = builder.createQuery(PredicateGroup.create(queryMap), resolver.adaptTo(Session.class));
			SearchResult result = query.getResult();
			if (result != null) {
				List<Hit> hits = result.getHits();
				for (Hit hit : hits) {
					String path = hit.getPath();
					PageManager pageManager = resolver.adaptTo(PageManager.class);
					Page rootPage = pageManager.getPage(path);
					if (null != rootPage) {
						String replicationStatus = rootPage.getProperties().get("cq:lastReplicationAction",
								String.class);
						// LOG.info("cq:lastReplicationAction is "
						// +replicationStatus);
						// res=resolver.getResource(jcrPath);
						// Node node=res.adaptTo(Node.class);
						// node.setProperty("shikha","soni");
						if (replicationStatus.equalsIgnoreCase("Deactivate")) {
							replicator.replicate(resolver.adaptTo(Session.class), ReplicationActionType.ACTIVATE,
									rootPage.getPath());
						}
					}
				}
			}
		}

		catch (LoginException | ReplicationException | RepositoryException e) {
			e.printStackTrace();
		}
	}

}
