package edu.cmu.sv.smartsense;

import android.os.AsyncTask;


public class SdasPlatformFacacde extends AsyncTask<SdasRequest, Void, Void> {

	@Override
	protected Void doInBackground(SdasRequest... params) {
		SdasRequest sdasRequest = params[0];
		SdasResponse sdasResponse = sdasRequest.perform();
		
		if(sdasResponse.responseCode != 200)
		{
			System.out.println("error");
		}
		
		System.out.println(sdasResponse.responseBody);
		
	//	if(sdasResponse.getResponseCode() == 200 && sdasResponse.getResponseBody().equals("saved"))
		//{
	//		System.out.println("Sensor reading saved");
	//	}
	//	else
	//	{
	//		System.out.println("Failed to save sensor reading");
	//	}
		return null;
	}
	
	

}
