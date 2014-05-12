/*
 * This product was created for CMU SV
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
	
	public static final int MAX_THREADS = 10;
	
	
	
	/** The Constant DEBUG_URL. */
	public static final String DEBUG_URL = "http://einstein.sv.cmu.edu:9000/";
	
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
		sensorNameMapping.put(Sensor.TYPE_ACCELEROMETER, "androidAccelerometer");
		sensorNameMapping.put(Sensor.TYPE_AMBIENT_TEMPERATURE, "androidTemperature");
		sensorNameMapping.put(Sensor.TYPE_GRAVITY, "androidGravity");
		sensorNameMapping.put(Sensor.TYPE_GYROSCOPE, "androidGyroscope");
		sensorNameMapping.put(Sensor.TYPE_GYROSCOPE_UNCALIBRATED, "androidGyroscope");
		sensorNameMapping.put(Sensor.TYPE_LIGHT, "androidLight");
		sensorNameMapping.put(Sensor.TYPE_LINEAR_ACCELERATION,
				"androidLinearAcceleration");
		sensorNameMapping.put(Sensor.TYPE_MAGNETIC_FIELD, "androidMageneticField");
		sensorNameMapping.put(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED,
				"androidMageneticField");
		sensorNameMapping.put(Sensor.TYPE_PROXIMITY, "androidProximity");
		sensorNameMapping.put(Sensor.TYPE_PRESSURE, "androidPressure");
		sensorNameMapping.put(Sensor.TYPE_RELATIVE_HUMIDITY, "androidHumidity");
		sensorNameMapping.put(Sensor.TYPE_ROTATION_VECTOR, "androidRotationVector");
		sensorNameMapping.put(Sensor.TYPE_GAME_ROTATION_VECTOR, "androidGameRotationVector");
		// Deprecated from API LEVEL 8. 
		// The way to do this is to provide a configuration page to configure
		// what sensor values need to be sent
		
		sensorNameMapping.put(Sensor.TYPE_ORIENTATION, "androidOrientationVector");

	}
	
	

}
