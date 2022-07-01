package com.learning.core.models;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learning.beans.Items;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = AemDemoMultifield.RESOURCE_TYPE)

@Exporter(name = "jackson", extensions = "json", selector = "geeks", options = {
		@ExporterOption(name = "SerializationFeature.WRAP_ROOT_VALUE", value = "true"),
		@ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true") })
public class AemDemoMultifield implements ExporterTestInterface{
	
	


	protected static final String RESOURCE_TYPE = "/apps/learning/components/content/aemdemotext";
	// "/apps/learning/components/content/etouchmulti";

	// @ChildResource(name="products",injectionStrategy=InjectionStrategy.OPTIONAL)
	// Resource multifield;

	@ValueMapValue
	String text;
	
	private String description="my description";
	
	

	// private List<Items> multifieldItemsName = new ArrayList<>();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "titleee";
	}

	// @PostConstruct
	// private void init(){
	//
	// Iterator<Resource> resource=multifield.listChildren();
	//
	// String title;
	// String description;
	// String imagepath;
	// while(resource.hasNext()) {
	//
	// Resource childNode=resource.next();
	// title=childNode.getValueMap().get("title",String.class);
	// description=childNode.getValueMap().get("description",String.class);
	// imagepath=childNode.getValueMap().get("imagepath",String.class);
	//
	// Items item = new Items();
	// item.setTitle(title.toUpperCase());
	// item.setDescription(description);
	// item.setImagepath(imagepath);
	//
	// multifieldItemsName.add(item);
	// }
	// }
//	@JsonProperty
//	public List<Items> getMultifieldItemsName() {
//		return multifieldItemsName;
//	}
}
