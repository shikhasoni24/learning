package com.learning.core.practice;


public class Topping {
	public String type;
    public String id;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public Topping(String type, String id) {
		super();
		this.type = type;
		this.id = id;
	}
	public Topping() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Topping [type=" + type + ", id=" + id + "]";
	}
    
    
}
