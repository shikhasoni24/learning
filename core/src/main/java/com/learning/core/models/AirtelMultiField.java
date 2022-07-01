package com.learning.core.models;

import java.util.ArrayList;


import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;

import com.learning.beans.Items;

@Model(adaptables = Resource.class,defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class AirtelMultiField {

	@ChildResource(name = "products", injectionStrategy = InjectionStrategy.OPTIONAL)
	Resource multifield;

	private List<Items> multifieldItemsName = new ArrayList<Items>();

	@PostConstruct
	private void init() {

		Iterator<Resource> resource = multifield.listChildren();

		String title;
		String description;
		String imagepath;

		while (resource.hasNext()) {

			Resource childNode = resource.next();
			title = childNode.getValueMap().get("title", String.class);
			description = childNode.getValueMap().get("description", String.class);
			imagepath = childNode.getValueMap().get("imagepath", String.class);
			
			Items item = new Items();
			item.setTitle(title.toUpperCase());
			item.setDescription(description);
			item.setImagepath(imagepath);

			multifieldItemsName.add(item);
		}
	}

	public List<Items> getMultifieldItemsName() {
		return multifieldItemsName;
	}
}
