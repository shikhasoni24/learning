package com.learning.core.practice;

import java.util.List;

public class Item {
	
	    public String name;
	    public String id;
	    public String type;
	    public String ppu;
	    public List<Topping> topping;
	    public List<Batter> batter;
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
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getPpu() {
			return ppu;
		}
		public void setPpu(String ppu) {
			this.ppu = ppu;
		}
		public List<Topping> getTopping() {
			return topping;
		}
		public void setTopping(List<Topping> topping) {
			this.topping = topping;
		}
		public List<Batter> getBatter() {
			return batter;
		}
		public void setBatter(List<Batter> batter) {
			this.batter = batter;
		}
		public Item(String name, String id, String type, String ppu, List<Topping> topping, List<Batter> batter) {
			super();
			this.name = name;
			this.id = id;
			this.type = type;
			this.ppu = ppu;
			this.topping = topping;
			this.batter = batter;
		}
		public Item() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "Item [name=" + name + ", id=" + id + ", type=" + type + ", ppu=" + ppu + ", topping=" + topping
					+ ", batter=" + batter + "]";
		}
	    
	    

	    
		
  
	}


