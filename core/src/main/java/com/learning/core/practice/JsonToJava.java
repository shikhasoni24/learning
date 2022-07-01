package com.learning.core.practice;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonToJava {

	public static void main(String[] args) {
		JsonToJava jss=new JsonToJava();

		String jsonData = "{\n  \"rfg\": [\n    {\n      \"name\": \"Sell Real Estate\",\n      \"id\": \"128026\",\n      \"guid\": \"29cf8516-3115-11d7-a2c7-00b0d0494558\",\n      \"countryName\": \"United States\",\n      \"countryCode\": \"US\",\n      \"isNrt\": false,\n      \"officeDetails\": [\n        {\n          \"name\": \"Coldwell Banker Sell Real Estate\",\n          \"id\": \"0001\",\n          \"guid\": \"25345885-35b8-11d7-a2c7-00b0d0494558\",\n          \"countryCode\": \"US\"\n        }\n      ]\n    },\n    {\n      \"name\": \"Top Team\",\n      \"id\": \"199041\",\n      \"guid\": \"24de7239-35b8-11d7-a2c7-00b0d0494558\",\n      \"countryName\": \"United States\",\n      \"countryCode\": \"US\",\n      \"isNrt\": false,\n      \"officeDetails\": [\n        {\n          \"name\": \"Coldwell Banker Top Team\",\n          \"id\": \"0001\",\n          \"guid\": \"2534659c-35b8-11d7-a2c7-00b0d0494558\",\n          \"countryCode\": \"US\"\n        }\n      ]\n    }\n  ]\n}";
		String str = "[\r\n  {\r\n    \"name\": \"Harry Potter\",\r\n    \"city\": \"London\"\r\n  },\r\n  {\r\n    \"name\": \"Don Quixote\",\r\n    \"city\": \"Madrid\"\r\n  },\r\n  {\r\n    \"name\": \"Joan of Arc\",\r\n    \"city\": \"Paris\"\r\n  },\r\n  {\r\n    \"name\": \"Rosa Park\",\r\n    \"city\": \"Alabama\"\r\n  }\r\n]";
		
		List<Company> companyList = new ArrayList<>();
		// JSONObject jsonObj=new JSONObject();
		try {
			JSONArray jsonArray = new JSONArray(str);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				System.out.println(jsonObj.get("name"));
				System.out.println(jsonObj.get("city"));
			}

			JSONObject obj = new JSONObject(jsonData);

			JSONArray jsonArr = obj.getJSONArray("rfg");
			
			for (int j = 0; j < jsonArr.length(); j++) {
				Company comp = new Company();
				List<OfficeDetail> officeDetailList = new ArrayList<>();

				JSONObject jsonObj = jsonArr.getJSONObject(j);
				comp.setName(jsonObj.getString("name"));
				comp.setId(jsonObj.getString("id"));
				comp.setGuid(jsonObj.getString("guid"));
				comp.setCountryName(jsonObj.getString("countryName"));
				comp.setCountryCode(jsonObj.getString("countryCode"));

				JSONArray jsonArr1 = jsonObj.getJSONArray("officeDetails");

				for (int k = 0; k < jsonArr1.length(); k++) {
					OfficeDetail officeDetail = new OfficeDetail();
 
					JSONObject ob = jsonArr1.getJSONObject(k);
					officeDetail.setName(ob.getString("id"));
					officeDetail.setCountryCode(ob.getString("countryCode"));
					officeDetail.setGuid(ob.getString("guid"));
					officeDetail.setId(ob.getString("id"));
					officeDetailList.add(officeDetail);
					
				}
				comp.setOfficeDetails(officeDetailList);
 				companyList.add(comp);
				jss.getCompanyDetails(companyList);
			}
			
		
			System.out.println(companyList);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public  void getCompanyDetails(List<Company> list){
		for(Company c:list){
			
			System.out.println(c.countryCode);
			List<OfficeDetail> l=c.officeDetails;
			for(OfficeDetail of:l){
				System.out.println(of.getName());
			}
			
		}
		
		
		
	}

}
