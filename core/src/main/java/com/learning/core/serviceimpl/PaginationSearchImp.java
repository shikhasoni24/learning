package com.learning.core.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.learning.core.service.PaginationSearch;

@Component(service = PaginationSearch.class, immediate = true)
public class PaginationSearchImp implements PaginationSearch {

	@Reference
	QueryBuilder querybuilder;
	@Reference
	ResourceResolverFactory resourceResolverFactory;

	public JSONObject search(String searchWord, int startIndex, int limit) {
		JSONObject searchresult = new JSONObject();
		try {
			Resource resource = null;
			Map<String, Object> params = new HashMap<>();
			params.put(ResourceResolverFactory.SUBSERVICE, "myUser");
			ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(params);
			Session session = resolver.adaptTo(Session.class);
			Query query = querybuilder.createQuery(PredicateGroup.create(getQuery(searchWord,startIndex,limit)), session);
			SearchResult result = query.getResult();
			List<Hit> hits = result.getHits();
			JSONArray jsonArray = new JSONArray();

			for (Hit hit : hits) {
				Page page = hit.getResource().adaptTo(Page.class);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("title", page.getTitle());
				jsonObject.put("path", page.getPath());
				jsonArray.put(jsonObject);
			}
			searchresult.put("results", jsonArray);

		} catch (LoginException | RepositoryException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchresult;
	}

	public Map<String, String> getQuery(String searchWord, int startIndex, int limit ) {
		Map<String, String> map = new HashMap<>();
		map.put("path", "/content/we-retail");
		map.put("type", "cq:Page");
		map.put("fulltext", searchWord);
		map.put("p.offset", Long.toString(startIndex));
		map.put("p.limit", Long.toString(limit));
		return map;
	}
	
}
