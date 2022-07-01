package com.learning.core.practice;

import java.util.List;

public class Company {
	
	    public String name;
	    public String id;
	    public String guid;
	    public String countryName;
	    public String countryCode;
	    public List<OfficeDetail> officeDetails;
	    
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getGuid() {
			return guid;
		}
		public void setGuid(String guid) {
			this.guid = guid;
		}
		public String getCountryName() {
			return countryName;
		}
		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}
		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		public List<OfficeDetail> getOfficeDetails() {
			return officeDetails;
		}
		public void setOfficeDetails(List<OfficeDetail> officeDetails) {
			this.officeDetails = officeDetails;
		}
		
		
		public Company() {
			
		}
		
		public Company(String name, String id, String guid, String countryName, String countryCode,
				List<OfficeDetail> officeDetails) {
			super();
			this.name = name;
			this.id = id;
			this.guid = guid;
			this.countryName = countryName;
			this.countryCode = countryCode;
			this.officeDetails = officeDetails;
		}
		
		@Override
		public String toString() {
			return "Company [name=" + name + ", id=" + id + ", guid=" + guid + ", countryName=" + countryName
					+ ", countryCode=" + countryCode + ", officeDetails=" + officeDetails + "]";
		}
  
	}


