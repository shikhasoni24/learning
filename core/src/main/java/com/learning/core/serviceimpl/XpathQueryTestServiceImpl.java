package com.learning.core.serviceimpl;

import javax.jcr.Node;

import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.learning.core.utility.ResourceResolverUtil;

@Component(service = XpathQueryTestServiceImpl.class, immediate = true)
public class XpathQueryTestServiceImpl {

	@Reference
	ResourceResolverFactory resourceResolverFactory;

	public JSONObject getParticularDateQuery() {
		Query query;
		NodeIterator nodeIterater = null;
		JSONObject obj=new JSONObject();
		try {
			ResourceResolver resolver = ResourceResolverUtil.newResolver(resourceResolverFactory);

			Session session = resolver.adaptTo(Session.class);
			QueryManager qm = session.getWorkspace().getQueryManager();

			StringBuilder xPath = new StringBuilder();
			xPath.append("/jcr:root/content/shikha//*");
			xPath.append("[((@jcr:created > xs:dateTime('2021-08-20T23:59:59.000+05:30')))]");
			String xPathQuery = xPath.toString();

			query = qm.createQuery(xPathQuery, Query.XPATH);
			int i = 0;
			
			QueryResult qr=query.execute();
			nodeIterater=qr.getNodes();
			while(nodeIterater.hasNext()){
				Node result=nodeIterater.nextNode();
				obj.put("path"+i, result.getPath());
				i++;
	
			}
		} catch (RepositoryException | LoginException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
		}
}
