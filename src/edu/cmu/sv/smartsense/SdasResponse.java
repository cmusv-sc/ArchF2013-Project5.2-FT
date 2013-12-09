/*
 * 
 */
package edu.cmu.sv.smartsense;

// TODO: Auto-generated Javadoc
/**
 * The Class SdasResponse.
 */
public class SdasResponse {
	
	/** The response code. */
	int responseCode;
	
	/** The response body. */
	String responseBody;
	
	/**
	 * Instantiates a new sdas response.
	 *
	 * @param responseCode the response code
	 */
	public SdasResponse(int responseCode) {
		this.responseCode = responseCode;
	    
	}
	
	/**
	 * Gets the response code.
	 *
	 * @return the response code
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * Gets the response body.
	 *
	 * @return the response body
	 */
	public String getResponseBody() {
		return responseBody;
	}	
	
	/**
	 * Sets the response body.
	 *
	 * @param responseBody the new response body
	 */
	public void setResponseBody (String responseBody) {
		this.responseBody = responseBody;
	}
	

}
