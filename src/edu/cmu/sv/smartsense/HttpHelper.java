package edu.cmu.sv.smartsense;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class HttpHelper {

	private static SdasResponse httpPostJson(String urlStr,
			JsonObject jsonObject) throws Exception {

		URL url = new URL(urlStr);
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

		// Change for new API level
		if (connection.getResponseCode() != 200) {
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

	public static SdasResponse sendData(String requestType, JsonObject json) {
		try {
			String url = Constants.DEBUG_URL + requestType;
			return HttpHelper.httpPostJson(url, json);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
