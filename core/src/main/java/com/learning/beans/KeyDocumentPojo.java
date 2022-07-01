package com.learning.beans;

import java.util.Comparator;
import java.util.Date;

public class KeyDocumentPojo {

	private String title;
	private String docPath;
	private String type;
	private long size;
	private Date date;
	private String displayDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDisplayDate() {
		return displayDate;
	}

	public void setDisplayDate(String displayDate) {
		this.displayDate = displayDate;
	}

	@Override
	public String toString() {
		return "KeyDocumentPojo [title=" + title + ", docPath=" + docPath + ", type=" + type + ", size=" + size
				+ ", date=" + date + ", displayDate=" + displayDate + "]";
	}

}

// class DateCompare implements Comparator<keyDocumentPojo>{
//
// public int compare(KeyDocumentPojo p1, KeyDocumentPojo p2){
// return p1.getDate().compareTo(p2.getDate());
// }
//
//
//
//
// }
