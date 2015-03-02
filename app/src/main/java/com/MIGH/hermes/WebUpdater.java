package com.MIGH.hermes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import android.content.Context;

import com.MIGH.hermes.CompanyModel;
import com.MIGH.hermes.DBAdapter;


/* #TODO
 * 1. Create HTTP Post 
 * 2. Post type of data request
 * 3. Get back, and parse data gotten back
 * 4. if Company Information, parse to Company Model objects and insert into database
 * 5. Profit 
 */
public class WebUpdater{
	public JSONObject jsonObject;
	public Context context;
	public String result=null;
	InputStream inputStream=null;
	HttpPost httpPost;
	HttpGet httpGet;
	DefaultHttpClient   httpclient;
	
	BasicHttpRequest webRequest;
	
	public WebUpdater(Context parentActivity ) {
		context = parentActivity;
		httpclient = new DefaultHttpClient(new BasicHttpParams());
		httpGet = new HttpGet();
		
		// Depends on your web service. This would be used for only HttpPosts
		//httppost.setHeader("Content-type", "application/json");

		
		
		
		
		
	}
	
	
	public JSONObject jsonGetter(String URL){
		// For now, only handles Get calls
		/*
		
		try {
		    HttpResponse response = httpclient.execute((HttpUriRequest) webRequest);           
		    HttpEntity entity = response.getEntity();

		    inputStream = entity.getContent();
		    // json is UTF-8 by default
		    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
		    StringBuilder sb = new StringBuilder();

		    String line = null;
		    while ((line = reader.readLine()) != null)
		    {
		        sb.append(line + "\n");
		    }
		    result = sb.toString();
		    jsonString = result;
		} catch (Exception e) { 
		    // Oops
		}
		finally {
		    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
		}
		*/
		
		
		return null;
	}
	
	
	
	public ArrayList<CompanyModel> CompanyParser(JSONObject companyJson){
		// Parses the json into the company model object or arraylist of companymodel objects
		
		
		ArrayList<CompanyModel> gainedCompanies = new ArrayList<CompanyModel>();
		JSONArray compArray = new JSONArray();
		try {
			 compArray = companyJson.getJSONArray("infoList");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0; i < compArray.length();i++){	 
			try {
				JSONObject object = compArray.getJSONObject(i);
				
				gainedCompanies.add(new CompanyModel(
						object.getString("id"),
						object.getString("name"),
						object.getString("telephone"),
						object.getString("location"),
						object.getString("logo"),
						object.getString("website"),
						object.getString("email"),
						object.getString("category"),
						object.getString("postal"),
						object.getString("description"),
						object.getString("gps"),
						object.getString("tags"),
						object.getString("advert"),
						object.getString("facebook"),
						object.getString("twitter"),
						object.getString("google")
						));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		
		return gainedCompanies;
	}
	
	public Boolean DatabaseInsert(ArrayList<CompanyModel> model){
		if (model.isEmpty()){
			return false;
		}
		
		DBAdapter insertionAdapter = new DBAdapter(context);
		insertionAdapter.open();
		
		
		for (int i=0; i <model.size();i++){
			insertionAdapter.insertRow(model.get(i));
		}
		
		return true;
	}
	
}