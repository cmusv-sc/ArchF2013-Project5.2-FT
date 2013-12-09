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
	 * Gets the request type.
	 *
	 * @return the request type
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * Sets the request type.
	 *
	 * @param requestType the new request type
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	/**
	 * Gets the json.
	 *
	 * @return the json
	 */
	public JsonObject getJson() {
		return json;
	}

	/**
	 * Sets the json.
	 *
	 * @param json the new json
	 */
	public void setJson(JsonObject json) {
		this.json = json;
	}

	
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
	
	
	/**
	 * Perform.
	 *
	 * @return the sdas response
	 */
	public SdasResponse perform()
	{
		return HttpHelper.sendData(this.requestType, this.json);
		
	}
	

}
