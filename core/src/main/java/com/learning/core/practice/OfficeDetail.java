package com.learning.core.practice;

public class OfficeDetail {
	 private String name;
	    private String id;
	    private String guid;
	    private String countryCode;
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
		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		public OfficeDetail(String name, String id, String guid, String countryCode) {
			super();
			this.name = name;
			this.id = id;
			this.guid = guid;
			this.countryCode = countryCode;
		}
		public OfficeDetail() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "OfficeDetail [name=" + name + ", id=" + id + ", guid=" + guid + ", countryCode=" + countryCode
					+ "]";
		}
	    
	    
}
