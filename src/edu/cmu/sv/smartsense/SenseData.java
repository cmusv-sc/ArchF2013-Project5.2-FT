/**
Copyright (c) 2013 Carnegie Mellon University Silicon Valley. 
All rights reserved. 

This program and the accompanying materials are made available
under the terms of dual licensing(GPL V2 for Research/Education
purposes). GNU Public License v2.0 which accompanies this distribution
is available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 

Please contact http://www.cmu.edu/silicon-valley/ if you have any 
questions.
**/
package edu.cmu.sv.smartsense;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * The Class SensorDataPublisher.
 */
public class SenseData extends Activity implements
SensorEventListener {

	/** The interval_seconds. */
	private final int interval_seconds = 5 * 1000;
	
	/** The m sensor manager. */
	private SensorManager mSensorManager;
	
	/** The m sensor list. */
	private List<Sensor> mSensorList;
	
	/** The ht. */
	private Hashtable<Integer, float[]> ht;
	
	/** The available sensors. */
	private Sensor[] availableSensors;

	/** The sensor name. */
	HashMap<Integer, String> sensorName = Constants.sensorNameMapping;
	
 

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		register_device();

		setContentView(R.layout.activity_sense_data);
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

		ht = new Hashtable<Integer, float[]>();

		registerSensor(mSensorList);

		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		LocationListener ll = new BasicLocationListener();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
		
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		Configuration.getInstance().set_device_id(telephonyManager.getDeviceId());

	}

	/**
	 * Register_device.
	 */
	private void register_device() {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String imeistring = telephonyManager.getDeviceId();
		Configuration.getInstance().set_device_id(imeistring);
		new Registration(imeistring);
	}

	// Method to register sensors
	/**
	 * Register sensor.
	 * 
	 * @param sensorList
	 *            the sensor list
	 */
	public void registerSensor(List<Sensor> sensorList) {
		int numberOfSensors = sensorList.size();
		Log.d("Number of Sensors", numberOfSensors + "");

		availableSensors = new Sensor[numberOfSensors];

		for (int i = 0; i < numberOfSensors; i++) {
			availableSensors[i] = mSensorManager.getDefaultSensor(sensorList
					.get(i).getType());
		}
	}

	// Method to convert sensor information to json
	/**
	 * Gets the sensor jsons.
	 * 
	 * @return the sensor jsons
	 */
	public List<JsonObject> getSensorJsons() {
		// For initial conditions where ht might be NULL
		if (ht == null || ht.size() == 0) {
			return null;
		}

		Date date = new java.util.Date();
		long timestamp = date.getTime();

		List<JsonObject> sensorDataJsons = new ArrayList<JsonObject>();

		Iterator<Integer> itr = ht.keySet().iterator();
		while (itr.hasNext()) {
			Integer key = itr.next(); // Key in the sensor type map
			float[] value = ht.get(key); // Float array fetched from sensors

			JsonObject sensorDataJson = new JsonObject();

			sensorDataJson.addProperty("id", Configuration.getInstance()
					.get_device_id());
			// TODO: Note value[0] only provides the X co ordinate of 3d sensors
			// like Gyroscope
			sensorDataJson.addProperty(sensorName.get(key), value[0]);
			sensorDataJson.addProperty("timestamp", timestamp);

			sensorDataJsons.add(sensorDataJson);
		}

		return sensorDataJsons;

	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	protected void onResume() {
		super.onResume();
		for (int i = 0; i < mSensorList.size(); i++) {
			mSensorManager.registerListener(this, availableSensors[i],
					SensorManager.SENSOR_DELAY_NORMAL);
		}
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	/* (non-Javadoc)
	 * @see android.hardware.SensorEventListener#onAccuracyChanged(android.hardware.Sensor, int)
	 */
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO: Adding a parameter for accuracy to the sensor reading being
		// published ?
	}

	/**
	 * Str.
	 * 
	 * @param obj
	 *            the object
	 * @return the string
	 */
	public static String str(Object obj) {
		String type = obj.getClass().getName();
		if (type.equals("Integer") || type.equals("Float")) {
			return String.valueOf(obj);
		} else
			return null;

	}

	/* (non-Javadoc)
	 * @see android.hardware.SensorEventListener#onSensorChanged(android.hardware.SensorEvent)
	 */
	public void onSensorChanged(SensorEvent event) {
		
		//Publish only after significant difference. Logic missing

		ht.put(event.sensor.getType(), event.values);
		
			List<JsonObject> list_json = getSensorJsons();
			Iterator<JsonObject> iter = list_json.iterator();

			while (iter.hasNext()) {
				JsonObject jsonObject = iter.next();
				Log.d("json obj populated", new Gson().toJson(jsonObject));

				SdasRequest req = new SdasRequest(
						SdasRequest.PUBLISH_SENSOR_READING, jsonObject);

				new SdasPlatformFacacde().execute(req);
			}
			

		

	}

	private class BasicLocationListener implements LocationListener {
		@Override
		public void onLocationChanged(Location location) {
			if (location != null) {
				Log.d("LOCATION CHANGED", location.getLatitude() + "");
				Log.d("LOCATION CHANGED", location.getLongitude() + "");
				Toast.makeText(SenseData.this,
						location.getLatitude() + "" + location.getLongitude(),
						Toast.LENGTH_LONG).show();
			}
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}
}



