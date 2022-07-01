package com.learning.core.service;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;

import com.learning.beans.KeyDocumentPojo;

public interface KeyDocumentService {

	
	List<KeyDocumentPojo> getKeyDocumentDetails(String linkPath, SlingHttpServletRequest request);
}
