package com.learning.core.service;

import java.util.Map;

import org.apache.sling.commons.json.JSONObject;

public interface PaginationSearch {

	public Map<String, String> getQuery(String searchWord,int startIndex,int limit);
		public JSONObject search(String searchWord,int startIndex,int limit);

}
