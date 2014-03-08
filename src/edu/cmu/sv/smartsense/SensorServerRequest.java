package edu.cmu.sv.smartsense;

import java.util.Date;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.*;


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
		JsonArray obj = new JsonArray();
		
		JsonObject sensorDataJson = new JsonObject();

		/*sensorDataJson.addProperty("sensorName", Configuration.getInstance()
								.get_device_id());*/
		sensorDataJson.addProperty("sensorName",name);
		sensorDataJson.addProperty("timestamp", timestamp);
		sensorDataJson.addProperty("value", String.valueOf(this.values[0]));
		//sensorDataJson.addProperty(this.name, this.values[0]);
		
		obj.add(sensorDataJson);
		
		
		Log.d("DeviceData",new Gson().toJson(obj));
						
		SdasRequest req = new SdasRequest(SdasRequest.PUBLISH_SENSOR_READING, obj);
						
		req.execute();
//		
//		System.out.println(name + "\nX" + Float.toString(values[0]) 
//							+  "\nY" + Float.toString(values[1])
//							+  "\nZ" + Float.toString(values[2]));
		
	}
	
	
	

}
