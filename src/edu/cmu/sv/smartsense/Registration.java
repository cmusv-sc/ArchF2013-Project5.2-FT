/*
 * 
 */
package edu.cmu.sv.smartsense;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

// TODO: Auto-generated Javadoc
/**
 * The Class Registration.
 */
public class Registration {

	/**
	 * Instantiates a new registration.
	 * 
	 * @param device_id
	 *            the device_id
	 */
	Registration(String device_id, String lat, String lng) {
		System.out.println("lat" + lat + "lng" + lng);
		JsonObject json = populateJson(device_id, lat, lng);
		addDevice(json);
	}

	/*
	 * API : 1.2
	 * 
	 * {"device_id" : <"device_name">,"device_type": <"device_type">,
	 * "device_agent": <"device_agent">, "network_address": <"network_address">,
	 * "location_description": <"location_description">, "latitude":
	 * <"latitude">, "longitude": <"longitude">, "altitude": <"altitude">,
	 * "position_format_system": <"position_format_system">,
	 * "user_defined_fields": <"user_defined_fields">}
	 */
	/**
	 * Populate json.
	 * 
	 * @param device_id
	 *            the device_id
	 * @return the json object
	 */
	private JsonObject populateJson(String device_id, String lat, String lng) {
		/*
		 * {"deviceTypeName": "fireimp", "uri": "www.device.com", "location" :
		 * {"representation": "test location description", "latitude": 10,
		 * "longitude": 10, "altitude": 10}, "deviceUserDefinedFields":
		 * "For test"}
		 */

		JsonObject sensorRegistration = new JsonObject();
		JsonObject sensorLocation = new JsonObject();
		sensorRegistration.addProperty("deviceTypeName", "bodevicetype");
		sensorRegistration.addProperty("uri", device_id + ".androidIMEI");
		/*sensorLocation.addProperty("representation", "cmu_sv");
		sensorLocation.addProperty("latitude", lat);
		sensorLocation.addProperty("longitude", lng);
		sensorLocation.addProperty("altitude", 0);
		sensorRegistration.add("location", sensorLocation);*/
		sensorRegistration.addProperty("deviceUserDefinedFields","for_test");

		// System.out.println(new Gson().toJson(sensorRegistration));
		Log.d("testRegistration", new Gson().toJson(sensorRegistration));
		return sensorRegistration;

	}

	/**
	 * Adds the device.
	 * 
	 * @param json
	 *            the json
	 */
	private void addDevice(JsonObject json) {
		SdasRequest sdasRequest = new SdasRequest(SdasRequest.ADD_DEVICE, json);
		// new SdasPlatformFacacde().execute(sdasRequest);

		// Will not work for new API need to handle the response code here
		// if(sdasResponse.getResponseCode() == 200 &&
		// sdasResponse.getResponseBody().equals("device saved"))
		// {
		// System.out.println("Successfully added device");
		// }
		// else
		// {
		// System.out.println("Failed to add device");
		// }
		//
	}

}
