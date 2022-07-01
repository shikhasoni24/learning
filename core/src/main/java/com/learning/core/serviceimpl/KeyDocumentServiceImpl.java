package com.learning.core.serviceimpl;

import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.day.cq.dam.api.Asset;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.day.cq.search.result.Hit;
import com.learning.beans.KeyDocumentPojo;
import com.learning.core.service.KeyDocumentService;


@Component(service=KeyDocumentService.class,name="KEY DOCUMENT SERVICE", immediate = true,configurationPolicy = ConfigurationPolicy.REQUIRE)
public class KeyDocumentServiceImpl implements KeyDocumentService {

	@Reference
	QueryBuilder builder;

	private List<KeyDocumentPojo> documentList;

	@Override
	public List<KeyDocumentPojo> getKeyDocumentDetails(String linkPath, SlingHttpServletRequest request) {
		ResourceResolver resolver = request.getResourceResolver();
		builder = resolver.adaptTo(QueryBuilder.class);
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("path", linkPath);
		queryMap.put("property", "jcr:primaryType");
		queryMap.put("property.value", "dam:Asset");
        queryMap.put("p.limit", "-1");

		Query query = builder.createQuery(PredicateGroup.create(queryMap), resolver.adaptTo(Session.class));
		SearchResult result = query.getResult();
		if (result != null) {
			documentList = new ArrayList<KeyDocumentPojo>();
			List<Hit> hits = result.getHits();
			for (Hit hit : hits) {
				try {
					String path = hit.getPath();
					Resource resource = resolver.getResource(path);
					if (resource != null) {
						KeyDocumentPojo pojo = new KeyDocumentPojo();
						String name = resource.getName();
						String docPath = resource.getPath();
						pojo.setDocPath(docPath);
						pojo.setTitle(name);

						ValueMap valueMap = resource.getValueMap();
						// Map<String,Object> metadata=asset.getMetadata();
						Set<String> keyset = valueMap.keySet();
						for (String key : keyset) {
							if (key.equalsIgnoreCase("jcr:created")) {
								Date date = valueMap.get(key, Date.class);
								SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
								String displayDate = sdf.format(date);
								pojo.setDate(date);
								pojo.setDisplayDate(displayDate);
							}
						}

						Asset asset = resource.adaptTo(Asset.class);
						Map<String, Object> metadata = asset.getMetadata();
						Set<String> keySet2 = metadata.keySet();
						for (String key : keySet2) {

							if (key.equalsIgnoreCase("dc:format")) {
								String format = (String) metadata.get(key);
								if (format.contains("pdf")) {
									pojo.setType("PDF");
								} else
									pojo.setType(format);

							} else if (key.equalsIgnoreCase("dam:size")) {
								Long size = (Long) metadata.get(key);
								pojo.setSize(size);
							}
						}
						documentList.add(pojo);
					}

				} catch (RepositoryException e) {
					e.printStackTrace();
					// TODO Auto-generated method stub
				}
			}
		}
		return documentList;
	}
}
