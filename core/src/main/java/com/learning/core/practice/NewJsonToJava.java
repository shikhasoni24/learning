package com.learning.core.practice;

import java.util.ArrayList;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewJsonToJava {

	public static void main(String[] args) {
		

		String jsonData = "[{\"id\":\"0001\",\"type\":\"donut\",\"name\":\"Cake\",\"ppu\":0.55,\"batters\":{\"batter\":[{\"id\":\"1001\",\"type\":\"Regular\"},{\"id\":\"1002\",\"type\":\"Chocolate\"},{\"id\":\"1003\",\"type\":\"Blueberry\"},{\"id\":\"1004\",\"type\":\"Devil'sFood\"}]},\"topping\":[{\"id\":\"5001\",\"type\":\"None\"},{\"id\":\"5002\",\"type\":\"Glazed\"},{\"id\":\"5005\",\"type\":\"Sugar\"},{\"id\":\"5007\",\"type\":\"PowderedSugar\"},{\"id\":\"5006\",\"type\":\"ChocolatewithSprinkles\"},{\"id\":\"5003\",\"type\":\"Chocolate\"},{\"id\":\"5004\",\"type\":\"Maple\"}]},{\"id\":\"0002\",\"type\":\"donut\",\"name\":\"Raised\",\"ppu\":0.55,\"batters\":{\"batter\":[{\"id\":\"1001\",\"type\":\"Regular\"}]},\"topping\":[{\"id\":\"5001\",\"type\":\"None\"},{\"id\":\"5002\",\"type\":\"Glazed\"},{\"id\":\"5005\",\"type\":\"Sugar\"},{\"id\":\"5003\",\"type\":\"Chocolate\"},{\"id\":\"5004\",\"type\":\"Maple\"}]},{\"id\":\"0003\",\"type\":\"donut\",\"name\":\"OldFashioned\",\"ppu\":0.55,\"batters\":{\"batter\":[{\"id\":\"1001\",\"type\":\"Regular\"},{\"id\":\"1002\",\"type\":\"Chocolate\"}]},\"topping\":[{\"id\":\"5001\",\"type\":\"None\"},{\"id\":\"5002\",\"type\":\"Glazed\"},{\"id\":\"5003\",\"type\":\"Chocolate\"},{\"id\":\"5004\",\"type\":\"Maple\"}]}]";

		List<Item> itemList = new ArrayList<>();
		try {
			JSONArray obj = new JSONArray(jsonData);

			for (int j = 0; j < obj.length(); j++) {
				JSONObject jsonObj = obj.getJSONObject(j);
				List<Topping> toppingList = new ArrayList<>();
				Item item = new Item();

				item.setId(jsonObj.get("id").toString());
				item.setType(jsonObj.getString("type"));
				item.setName(jsonObj.getString("name"));
				item.setPpu(jsonObj.get("ppu").toString());
				JSONObject batters = (JSONObject) jsonObj.get("batters");
				JSONArray batter = batters.getJSONArray("batter");
				List<Batter> batterList = new ArrayList<>();
				
				for (int k = 0; k < batter.length(); k++) {
					JSONObject jsonOb = batter.getJSONObject(k);
					Batter b = new Batter();
					b.setId(jsonOb.getString("id"));
					b.setType(jsonOb.getString("type"));
					batterList.add(b);
				}
				
				item.setBatter(batterList);
				JSONArray topping = jsonObj.getJSONArray("topping");
				
				for (int k = 0; k < topping.length(); k++) {
					JSONObject jsonOb = topping.getJSONObject(k);
					Topping t = new Topping();
					t.setId(jsonOb.getString("id"));
					t.setType(jsonOb.getString("type"));
					toppingList.add(t);
				}
				
				item.setTopping(toppingList);
				itemList.add(item);

			}
			System.out.println(itemList);

		}

		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
