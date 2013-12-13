/*
 * 
 */
package edu.cmu.sv.smartsense;

import java.util.HashMap;

import android.hardware.Sensor;

// TODO: Auto-generated Javadoc
/**
 * The Class Constants.
 */
public final class Constants {
	

	
	/** The Constant PROUDUCTION. */
	public static final int PROUDUCTION = 0x01;
	
	/** The Constant DEBUG. */
	public static final int DEBUG = 0x02;
	
	/** The Constant TEST. */
	public static final int TEST = 0x03;
	
	
	
	/** The Constant DEBUG_URL. */
	public static final String DEBUG_URL = "http://einstein.sv.cmu.edu/";
	
	/** The Constant PRODUCITON_URL. */
	public static final String PRODUCITON_URL = "http://einstein.sv.cmu.edu:9000/";
	
	/** The Constant TEST_URL. */
	public static final String TEST_URL = "";
	
	/** The Constant ENVIRONMENT. */
	public static final int ENVIRONMENT = PROUDUCTION;
	
	/** The Constant sensorNameMapping. */
	public final static HashMap<Integer, String> sensorNameMapping;
	static {
		sensorNameMapping = new HashMap<Integer, String>();
		sensorNameMapping.put(Sensor.TYPE_ACCELEROMETER, "Accelerometer");
		sensorNameMapping.put(Sensor.TYPE_AMBIENT_TEMPERATURE, "Temperature");
		sensorNameMapping.put(Sensor.TYPE_GRAVITY, "Gravity");
		sensorNameMapping.put(Sensor.TYPE_GYROSCOPE, "Gyroscope");
		sensorNameMapping.put(Sensor.TYPE_GYROSCOPE_UNCALIBRATED, "Gyroscope");
		sensorNameMapping.put(Sensor.TYPE_LIGHT, "Light");
		sensorNameMapping.put(Sensor.TYPE_LINEAR_ACCELERATION,
				"LinearAcceleration");
		sensorNameMapping.put(Sensor.TYPE_MAGNETIC_FIELD, "MageneticField");
		sensorNameMapping.put(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED,
				"MageneticField");
		sensorNameMapping.put(Sensor.TYPE_PROXIMITY, "Proximity");
		sensorNameMapping.put(Sensor.TYPE_PRESSURE, "Pressure");
		sensorNameMapping.put(Sensor.TYPE_RELATIVE_HUMIDITY, "Humidity");
		sensorNameMapping.put(Sensor.TYPE_ROTATION_VECTOR, "RotationVector");
		sensorNameMapping.put(Sensor.TYPE_GAME_ROTATION_VECTOR, "GameRotationVector");
		// Deprecated from API LEVEL 8. 
		// The way to do this is to provide a configuration page to configure
		// what sensor values need to be sent
		
		sensorNameMapping.put(Sensor.TYPE_ORIENTATION, "OrientationVector");

	}
	
	

}
