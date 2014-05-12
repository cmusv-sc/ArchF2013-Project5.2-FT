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

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

// TODO: Auto-generated Javadoc
/**
* The Class SensorDataPublisher.
*/
public class SenseData extends Activity implements SensorEventListener,LocationListener {

/** The interval_seconds. */
private final int interval_seconds = 10 * 1000;

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

private String lat,lng;

LocationManager lm;

ExecutorService httpRequestServicePool;

public boolean onOptionsItemSelected(MenuItem item) {
switch(item.getItemId()){
case R.id.action_settings:
{
getFragmentManager().beginTransaction()
.replace(android.R.id.content, new UserSettings())
.commit();
break;
}

}
return super.onOptionsItemSelected(item);

}



@Override
public final void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);




setContentView(R.layout.activity_sense_data);
mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
mSensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

ht = new Hashtable<Integer, float[]>();

registerSensor(mSensorList);

lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
Configuration.getInstance().set_device_id(
telephonyManager.getDeviceId());


lat = lng = "0";

register_device();

httpRequestServicePool = Executors.newFixedThreadPool(Constants.MAX_THREADS);

}
@Override
public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
getMenuInflater().inflate(R.menu.main, menu);
return true;
}

/**
* Register_device.
*/
private void register_device() {
TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
String imeistring = telephonyManager.getDeviceId();
Configuration.getInstance().set_device_id(imeistring);
if(lm != null)
{
Location l = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
if(l != null)
{
this.lat = l.getLatitude() + "";
this.lng = l.getLongitude() + "";
Log.d("LOCATION", " LAT \t" + this.lat + "LONG\t" + this.lng);
new Registration(imeistring,lat,lng);
}
}
}

// Method to register sensors
/**
* Register sensor.
*
* @param sensorList
* the sensor list
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
// public List<JsonObject> getSensorJsons() {
// // For initial conditions where ht might be NULL
// if (ht == null || ht.size() == 0) {
// return null;
// }
//
// Date date = new java.util.Date();
// long timestamp = date.getTime();
//
// //List<JsonObject> sensorDataJsons = new ArrayList<JsonObject>();
//
// Iterator<Integer> itr = ht.keySet().iterator();
// while (itr.hasNext()) {
// Integer key = itr.next(); // Key in the sensor type map
// float[] value = ht.get(key); // Float array fetched from sensors
// if(value[1] != 0.0 || value[2]!= 0.0)
// {
// JsonObject sensorDataJson = new JsonObject();
//
// sensorDataJson.addProperty("id", Configuration.getInstance()
// .get_device_id());
// sensorDataJson.addProperty(sensorName.get(key) +"Y", value[1]);
// sensorDataJson.addProperty("timestamp", timestamp);
//
// // sensorDataJsons.add(sensorDataJson);
//
//
// sensorDataJson = new JsonObject();
//
//
// sensorDataJson.addProperty("id", Configuration.getInstance()
// .get_device_id());
// sensorDataJson.addProperty(sensorName.get(key) +"Z", value[2]);
// sensorDataJson.addProperty("timestamp", timestamp);
//
// //sensorDataJsons.add(sensorDataJson);
// }
//
// // JsonObject sensorDataJson = new JsonObject();
//
//// sensorDataJson.addProperty("id", Configuration.getInstance()
// // .get_device_id());
// // TODO: Note value[0] only provides the X co ordinate of 3d sensors
// // like Gyroscope
// // sensorDataJson.addProperty(sensorName.get(key), value[0]);
// // sensorDataJson.addProperty("timestamp", timestamp);
//
// // sensorDataJsons.add(sensorDataJson);
// }
//
// return sensorDataJsons;
//
// }

/*
* (non-Javadoc)
*
* @see android.app.Activity#onResume()
*/
protected void onResume() {
super.onResume();
for (int i = 0; i < mSensorList.size(); i++) {
mSensorManager.registerListener(this, availableSensors[i],
SensorManager.SENSOR_DELAY_NORMAL);
}
}

/*
* (non-Javadoc)
*
* @see android.app.Activity#onPause()
*/
protected void onPause() {
super.onPause();
mSensorManager.unregisterListener(this);
}

/*
* (non-Javadoc)
*
* @see
* android.hardware.SensorEventListener#onAccuracyChanged(android.hardware
* .Sensor, int)
*/
public void onAccuracyChanged(Sensor sensor, int accuracy) {
// TODO: Adding a parameter for accuracy to the sensor reading being
// published ?
}

/**
* Str.
*
* @param obj
* the object
* @return the string
*/
public static String str(Object obj) {
String type = obj.getClass().getName();
if (type.equals("Integer") || type.equals("Float")) {
return String.valueOf(obj);
} else
return null;

}

public void pushData(String sensorName, float[] sensorValues)
{
httpRequestServicePool.execute(new SensorServerRequest(sensorName,sensorValues));


}


/*
* (non-Javadoc)
*
* @see
* android.hardware.SensorEventListener#onSensorChanged(android.hardware
* .SensorEvent)
*/
public void onSensorChanged(SensorEvent event) {



final int type = event.sensor.getType();
final float val[] = event.values;


// Use the below stuff to decide what data to push
// switch(type)
// {
// case Sensor.TYPE_ACCELEROMETER: break;
// case Sensor.TYPE_AMBIENT_TEMPERATURE: break;
// case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR: break;
// case Sensor.TYPE_LIGHT: {
// break;
// }
// case Sensor.TYPE_GYROSCOPE: break;
// case Sensor.TYPE_STEP_COUNTER: break;
// case Sensor.TYPE_RELATIVE_HUMIDITY: break;
// case Sensor.TYPE_STEP_DETECTOR: break;
// case Sensor.TYPE_PRESSURE: break;
// }

pushData(Constants.sensorNameMapping.get(type), val);



}

/*
* (non-Javadoc)
*
* @see
* android.location.LocationListener#onLocationChanged(android.location
* .Location)
*/
@Override
public void onLocationChanged(Location location) {

}

@Override
public void onProviderDisabled(String provider) {
}

/*
* (non-Javadoc)
*
* @see
* android.location.LocationListener#onProviderEnabled(java.lang.String)
*/
@Override
public void onProviderEnabled(String provider) {
}

/*
* (non-Javadoc)
*
* @see
* android.location.LocationListener#onStatusChanged(java.lang.String,
* int, android.os.Bundle)
*/
@Override
public void onStatusChanged(String provider, int status, Bundle extras) {
}
}