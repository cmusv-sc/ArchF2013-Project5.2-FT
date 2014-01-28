/*
 * 
 */
package edu.cmu.sv.smartsense;

import com.google.gson.JsonObject;

// TODO: Auto-generated Javadoc
/**
 * The Class SdasRequest.
 */
public class SdasRequest {
	
	/** The Constant PUBLISH_SENSOR_READING. */
	public static final String PUBLISH_SENSOR_READING="sensors";
	
	/** The Constant ADD_DEVICE. */
	public static final String ADD_DEVICE= "add_device";
	
	/** The Constant ADD_SENSOR. */
	public static final String ADD_SENSOR= "";

	/** The request type. */
	private String requestType;
	
	/** The json. */
	private JsonObject json;

	
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
	
	public SdasResponse execute()
	{
		SdasResponse response = HttpHelper.sendData(this.requestType, this.json);
		return response;
	}
	

}
