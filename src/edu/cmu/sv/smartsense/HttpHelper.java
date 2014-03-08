/*
 * 
 */
package edu.cmu.sv.smartsense;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

// TODO: Auto-generated Javadoc
/**
 * The Class HttpHelper.
 */
public class HttpHelper {
	
	

	/**
	 * Http post json.
	 *
	 * @param urlStr the url str
	 * @param jsonObject the json object
	 * @return the sdas response
	 * @throws Exception the exception
	 */
	private static SdasResponse httpPostJson(String urlStr,
			JsonElement jsonObject) throws Exception {

		URL url = new URL(urlStr);
		Log.d("URL", urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");
		connection.setDoOutput(true);

		OutputStream out = null;
		Writer writer = null;
		try {
			out = connection.getOutputStream();
			writer = new OutputStreamWriter(out, "UTF-8");
			writer.write((new Gson()).toJson(jsonObject));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writer.close();
			out.close();
		}
		SdasResponse sdasResponse = new SdasResponse(
				connection.getResponseCode());

		
		if (connection.getResponseCode() != 201) {
			throw new IOException(connection.getResponseMessage());
		}
		StringBuilder sb;
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
		} finally {
			rd.close();
		}

		connection.disconnect();
		sdasResponse.setResponseBody(sb.toString());
		return sdasResponse;
	}

	/**
	 * Send data.
	 *
	 * @param requestType the request type
	 * @param json the json
	 * @return the sdas response
	 */
	public static SdasResponse sendData(String requestType, JsonElement json) {
		try {
			String url = Constants.DEBUG_URL + requestType;
			return HttpHelper.httpPostJson(url, json);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
