/*
 * 
 */
package edu.cmu.sv.smartsense;

import com.google.gson.*;

// TODO: Auto-generated Javadoc
/**
 * The Class SdasRequest.
 */
public class SdasRequest {
	
	/** The Constant PUBLISH_SENSOR_READING. */
	public static final String PUBLISH_SENSOR_READING="addSensorReading";
	
	/** The Constant ADD_DEVICE. */
	public static final String ADD_DEVICE= "addDevice";
	
	/** The Constant ADD_SENSOR. */
	public static final String ADD_SENSOR= "addSensor";

	/** The request type. */
	private String requestType;
	
	/** The json. */
	private JsonElement json;
	
	/**
	 * Instantiates a new sdas request.
	 *
	 * @param requestType the request type
	 * @param json the json
	 */
	public SdasRequest(String requestType, JsonObject json) {
		this.requestType = requestType;
		this.json = json;
		
		
	}
	
	public SdasRequest(String requestType, JsonArray json) {
		this.requestType = requestType;
		this.json = json;
		
		
	}
	
	public SdasResponse execute()
	{
		SdasResponse response = HttpHelper.sendData(this.requestType, this.json);
		return response;
	}
	

}
