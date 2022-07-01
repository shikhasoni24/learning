package com.learning.beans;

import java.util.ArrayList;
import java.util.List;

public class Items {
	private List<Items> multifieldItemsName = new ArrayList<Items>();
private String title;
	private String description;
	private String imagepath;

	public List<Items> getMultifieldItemsName() {
		return multifieldItemsName;
	}

	public void setMultifieldItemsName(List<Items> multifieldItemsName) {
		this.multifieldItemsName = multifieldItemsName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

}
