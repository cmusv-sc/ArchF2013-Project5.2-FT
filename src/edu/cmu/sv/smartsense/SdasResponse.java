package edu.cmu.sv.smartsense;

public class SdasResponse {
	
	int responseCode;
	
	String responseBody;
	
	public SdasResponse(int responseCode) {
		this.responseCode = responseCode;
	    
	}
	
	public int getResponseCode() {
		return responseCode;
	}

	public String getResponseBody() {
		return responseBody;
	}	
	
	public void setResponseBody (String responseBody) {
		this.responseBody = responseBody;
	}
	

}
