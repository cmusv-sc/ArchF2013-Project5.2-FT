/*
 * 
 */
package edu.cmu.sv.smartsense;

import com.google.gson.JsonObject;

// TODO: Auto-generated Javadoc
/**
 * The Class Registration.
 */
public class Registration {
	
	
	
	
	
	 /**
 	 * Instantiates a new registration.
 	 *
 	 * @param device_id the device_id
 	 */
 	Registration(String device_id, String lat, String lng)
	{
 		System.out.println("lat" + lat + "lng" + lng);
		JsonObject json = populateJson(device_id,lat,lng);
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
	 /**
 * Populate json.
 *
 * @param device_id the device_id
 * @return the json object
 */
private JsonObject populateJson(String device_id, String lat, String lng)
	 {
		 JsonObject json= new JsonObject();
		 json.addProperty("device_id",device_id);
		 json.addProperty("device_agent","android");
		 json.addProperty("network_address",device_id);
		 json.addProperty("location_description", "lat:"+ lat + "long:"+ lng);

		 json.addProperty("latitude",lat);
		 json.addProperty("longitude",lng);
		 json.addProperty("altitude","0");
		 json.addProperty("position_format_system", "");
		 json.addProperty("user_defined_fields", "{ uri : "+ device_id + "}")	;
		 
		 return json;
		 
		 
		 
	 }
	 
	 /**
 	 * Adds the device.
 	 *
 	 * @param json the json
 	 */
 	private void addDevice(JsonObject json)
	 {
		 SdasRequest sdasRequest = new SdasRequest(SdasRequest.ADD_DEVICE, json);
	//	 new SdasPlatformFacacde().execute(sdasRequest);
		
		 
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
