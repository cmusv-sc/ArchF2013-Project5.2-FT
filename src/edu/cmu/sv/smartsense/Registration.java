package edu.cmu.sv.smartsense;

import com.google.gson.JsonObject;

public class Registration {
	
	
	
	
	
	 Registration(String device_id)
	{
		
		JsonObject json = populateJson(device_id);
		addDevice(json);
	}
	 
/*
 *  API : 1.2 
 * 
 *  {"device_id" : <"device_name">,"device_type": <"device_type">, "device_agent": <"device_agent">, 
 *  "network_address": <"network_address">, "location_description": <"location_description">, 
 *  "latitude": <"latitude">, "longitude": <"longitude">, "altitude": <"altitude">, 
 *  "position_format_system": <"position_format_system">, "user_defined_fields": <"user_defined_fields">}
 * 
 * 	 
 */
	 private JsonObject populateJson(String device_id)
	 {
		 JsonObject json= new JsonObject();
		 json.addProperty("device_id",device_id);
		 json.addProperty("device_agent","android");
		 json.addProperty("network_address","127.0.0.1");
		 json.addProperty("location_description", "dynamic");

		 json.addProperty("latitude","0");
		 json.addProperty("longitude","0");
		 json.addProperty("altitude","0");
		 json.addProperty("position_format_system", "");
		 json.addProperty("user_defined_fields", "testing");
		 
		 return json;
		 
		 
		 
	 }
	 
	 private void addDevice(JsonObject json)
	 {
		 SdasRequest sdasRequest = new SdasRequest(SdasRequest.ADD_DEVICE, json);
		 new SdasPlatformFacacde().execute(sdasRequest);
		 
		 // Will not work for new API need to handle the response code here
//		 if(sdasResponse.getResponseCode() == 200 && sdasResponse.getResponseBody().equals("device saved"))
//		 {
//			 System.out.println("Successfully added device");
//		 }
//		 else
//		 {
//			 System.out.println("Failed to add device");
//		 }
//		 
	 }
	

}
