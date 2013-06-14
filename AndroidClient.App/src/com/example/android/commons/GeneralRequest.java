package com.example.android.commons;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.example.android.commons.Util;
import com.example.android.exceptions.ExampleException;

public class GeneralRequest {
	
	private static final String LOG_TAG = "General_Request";
	
	public GeneralRequest(Context context) {
		init(context);
	}

	public void init(Context context) {
		if (context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
			Log.w(LOG_TAG,
					"App miss permission android.permission.ACCESS_NETWORK_STATE! "
					+ "Some mobile's WebView don't display page!");
		}
	}
	
	public static HttpResponse GetRequest(String uri, Bundle getParams) throws ClientProtocolException, IOException
	{
		uri = uri + "?" + Util.GetParamsString(getParams);
		return Get(uri);
	}
	
	public static HttpResponse AccessTokenGetRequest(String uri, Bundle getParams) throws ExampleException, ClientProtocolException, IOException {
		uri = uri + "?" + Util.GetAccessTokenParamsString(getParams);
		return Get(uri);
	}
	
	private static HttpResponse Get(String uri) throws ClientProtocolException, IOException
	{
		HttpGet request=new HttpGet(uri);
		request.addHeader("Content-Type", "application/json; charset=utf-8");
		
		//HttpClient client = new DefaultHttpClient();
        // 设置请求超时
        //client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        // 设置读取超时
        //client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
		
		return new DefaultHttpClient().execute(request);
	}
	
	public static HttpResponse PutRequest(String uri, Bundle getParams, Bundle postParams) throws JSONException, ClientProtocolException, IOException
	{
		uri = uri + "?" + Util.GetParamsString(getParams);
		return Put(uri,postParams);
	}
	
	public static HttpResponse AccessTokenPutRequest(String uri, Bundle getParams, Bundle postParams) throws JSONException, ClientProtocolException, IOException, ExampleException
	{
		uri = uri + "?" + Util.GetAccessTokenParamsString(getParams);
		return Put(uri,postParams);
	}
	
	private static HttpResponse Put(String uri, Bundle postParams) throws JSONException, ClientProtocolException, IOException
	{
		HttpPut request=new HttpPut(uri);
		request.addHeader("Content-Type", "application/json; charset=utf-8");
		
		HttpEntity bodyEntity = new StringEntity(Util.JsonParmsString(postParams), "utf8");
		request.setEntity(bodyEntity);
		return new DefaultHttpClient().execute(request);		
	}
	
	public static HttpResponse PostRequest(String uri, Bundle getParams, Bundle postParams) throws JSONException, ClientProtocolException, IOException
	{
		uri = uri + "?" + Util.GetParamsString(getParams);
		return Post(uri, postParams);	
	}
	
	public static HttpResponse AccessTokenPostRequest(String uri, Bundle getParams, Bundle postParams) throws JSONException, ClientProtocolException, IOException, ExampleException
	{
		uri = uri + "?" + Util.GetAccessTokenParamsString(getParams);
		return Post(uri, postParams);	
	}
		
	private static HttpResponse Post(String uri, Bundle postParams) throws JSONException, ClientProtocolException, IOException
	{
		HttpPost request=new HttpPost(uri);
		request.addHeader("Content-Type", "application/json; charset=utf-8");

		HttpEntity bodyEntity = new StringEntity(Util.JsonParmsString(postParams), "utf8");
		request.setEntity(bodyEntity);
		return new DefaultHttpClient().execute(request);		
	}

}