package edu.cmu.sv.smartsense;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class SensorServerRequest implements Runnable {
	private String name;
	private float[] values;
	
	
	public SensorServerRequest(String sensorName, float[] values) {
		this.name = sensorName;
		this.values = values;
	}
	
	@Override
	public void run() {
		Date date = new java.util.Date();
		long timestamp = date.getTime();
		
		JsonObject sensorDataJson = new JsonObject();

		sensorDataJson.addProperty("id", Configuration.getInstance()
								.get_device_id());
		sensorDataJson.addProperty(this.name, this.values[0]);
		sensorDataJson.addProperty("timestamp", timestamp);
		
		System.out.println(new Gson().toJson(sensorDataJson));
						
		SdasRequest req = new SdasRequest(SdasRequest.PUBLISH_SENSOR_READING, sensorDataJson);
						
		req.execute();
//		
//		System.out.println(name + "\nX" + Float.toString(values[0]) 
//							+  "\nY" + Float.toString(values[1])
//							+  "\nZ" + Float.toString(values[2]));
		
	}
	
	
	

}
