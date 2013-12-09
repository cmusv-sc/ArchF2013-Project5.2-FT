package edu.cmu.sv.smartsense;

import com.google.gson.JsonObject;

public class SdasRequest {
	
	public static final String PUBLISH_SENSOR_READING="sensors";
	public static final String ADD_DEVICE= "add_device";
	public static final String ADD_SENSOR= "";

	private String requestType;
	private JsonObject json;
	
	
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public JsonObject getJson() {
		return json;
	}

	public void setJson(JsonObject json) {
		this.json = json;
	}

	
	public SdasRequest(String requestType, JsonObject json) {
		this.requestType = requestType;
		this.json = json;
	}
	
	
	public SdasResponse perform()
	{
		return HttpHelper.sendData(this.requestType, this.json);
		
	}
	

}
