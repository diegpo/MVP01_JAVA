package com.api.consomeapi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ConsumidorApi {
	private static String URLBase ="http://localhost:8080/api/"; 
	
	private static ConsumidorApi instance;
	
	private CloseableHttpClient clientHTTP;
	
	private ConsumidorApi() {
		this.clientHTTP = HttpClients.createDefault();
	}
	
	public static ConsumidorApi getInstance() {
		if(instance == null) {
			instance = new ConsumidorApi();
		}
		return instance;
	}
	
	public String doRequest(String path) {
		String  responseBody = null;
		try {
			HttpGet httpGet = new HttpGet(ConsumidorApi.URLBase+path);
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(
					final HttpResponse response) throws ClientProtocolException, IOException{
					int status = response.getStatusLine().getStatusCode();
					if(status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;					
					}else { 
						throw new ClientProtocolException("Falha inexperada de conexão: " + status);
					}
				}
			};
			responseBody = this.clientHTTP.execute(httpGet, responseHandler);
			System.out.println("---------------------------------------------");
			
		} catch (Exception ex) {
			
			Logger.getLogger(ConsumidorApi.class.getName()).log(Level.SEVERE, null, ex);
		}
		return responseBody;
	}
}