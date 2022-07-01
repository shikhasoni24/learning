package com.learning.core.models;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.learning.beans.KeyDocumentPojo;
import com.learning.core.service.KeyDocumentService;

@Model(adaptables = { Resource.class,
		SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class KeyDocumentModel {

	@Self
	SlingHttpServletRequest request;

	@Self
	Resource resource;

	@OSGiService
	private KeyDocumentService keyDoc;

	@ValueMapValue 
	private String documentRootPath;

	private List<KeyDocumentPojo> keyDocumentDetails;
	private KeyDocumentPojo pojo;
	private String title;
	private String type;
	private long size;
	private String displayDate;
	private String docPath;

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public long getSize() {
		return size;
	}

	public String getDisplayDate() {
		return displayDate;
	}

	public String getDocPath() {
		return docPath;
	}

	@PostConstruct
	private void init() {

		if (resource != null && request != null && documentRootPath != null) {
			keyDocumentDetails = keyDoc.getKeyDocumentDetails(documentRootPath, request);
			pojo = keyDocumentDetails.get(0);
			title = pojo.getTitle();
			type = pojo.getType();
			size = pojo.getSize();
			displayDate = pojo.getDisplayDate();
			docPath = pojo.getDocPath();

		}
	}
}
