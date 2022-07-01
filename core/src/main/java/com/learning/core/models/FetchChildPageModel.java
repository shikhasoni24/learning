package com.learning.core.models;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import com.learning.beans.ChildPageDetails;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;

@Model(adaptables = { Resource.class, SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FetchChildPageModel {

	@ValueMapValue
	String pagePath;

	@SlingObject
	private ResourceResolver resourceResolver;

	private List<ChildPageDetails> childPageDetails = new ArrayList<ChildPageDetails>();

	@PostConstruct
	private void init() {
		Resource resource = resourceResolver.getResource(pagePath);
		if (resource != null) {
			Page parentPage = resource.adaptTo(Page.class);
			if (parentPage != null) {
				Iterator<Page> listChildPages = parentPage.listChildren();
				while (listChildPages.hasNext()) {
					Page childPage = listChildPages.next();
					ChildPageDetails detail = new ChildPageDetails();
					detail.setTitle(childPage.getTitle());
					detail.setDescription(childPage.getDescription());
					childPageDetails.add(detail);

				}

			}

		}

	}

	public List<ChildPageDetails> getChildPageDetails() {
		return childPageDetails;

	}
}
