package edu.cmu.sv.smartsense;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SensorRegistration {

	/**
	 * Instantiates a new registration.
	 * 
	 * @param device_id
	 *            the device_id
	 */
	SensorRegistration(String[] sensorName, String sensorType, String deviceURI) {
		JsonObject json = populateJson(sensorName, sensorType, deviceURI);
		addSensor(json);
	}

	/**
	 * Populate json.
	 * 
	 * @param device_id
	 *            the device_id
	 * @return the json object
	 */
	private JsonObject populateJson(String[] sensorName, String sensorType,
			String deviceURI) {

		/*
		 * {"sensorName": "TestSensor", "sensorTypeName": "Humidity",
		 * "deviceUri": "www.testsensor.com", "sensorUserDefinedFields":
		 * "Test only"}
		 */
		JsonObject sensorRegistration = new JsonObject();
		for (int i = 0; i < sensorName.length; i++) {

			sensorRegistration.addProperty("sensorName", sensorName[i]);
			sensorRegistration.addProperty("sensorTypeName", sensorType);
			sensorRegistration.addProperty("deviceUri", deviceURI);
			Log.d("testSensorRegn", new Gson().toJson(sensorRegistration));

		
		return sensorRegistration;
		}
		return null;
	}

	/**
	 * Adds the device.
	 * 
	 * @param json
	 *            the json
	 */
	private void addSensor(JsonObject json) {
		SdasRequest sdasRequest = new SdasRequest(SdasRequest.ADD_SENSOR, json);

	}

}
