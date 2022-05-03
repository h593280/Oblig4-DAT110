package no.hvl.dat110.aciotdevice.client;

import java.io.IOException; 

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestClient {

	public RestClient() {}

	private static String logpath = "/accessdevice/log";

	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message
		
		OkHttpClient client = new OkHttpClient();
		
		Gson gson = new Gson();
		
		String json = gson.toJson(new AccessMessage(message), AccessMessage.class);
		
		RequestBody body = RequestBody
				.create(MediaType.parse("application/json"), json);
				
		
		Request request = new Request.Builder()
				.url("http://"+ Configuration.host+Configuration.port+logpath)
				.post(body)
				.build();
		
		
		try (Response response = client.newCall(request).execute()) {
		      System.out.println (response.body().string());
		    }
	   catch (IOException e) {
		   e.printStackTrace();
	   }
		
	}
	
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {

		AccessCode code = null;
		
		// TODO: implement a HTTP GET on the service to get current access code
		
		OkHttpClient client = new OkHttpClient();
		
		Request request = new Request.Builder()
				.url("http://"+ Configuration.host+Configuration.port+codepath)
				.get()
				.build();
		
		
		try (Response response = client.newCall(request).execute()) {
		    Gson gson = new Gson(); 
			code = gson.fromJson(response.body().string(), AccessCode.class);
		    }
	   catch (Exception e) {
		   e.printStackTrace();
	   }
		
		return code;
	}
}
