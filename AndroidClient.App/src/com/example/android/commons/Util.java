package com.example.android.commons;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.android.exceptions.ExampleException;
import com.example.android.extensions.DateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import android.os.Bundle;
import android.util.Log;

public final class Util {

    public static final String LOG_TAG = "Example-Util";

    public static void logger(String message) {
        Log.i(LOG_TAG, message);
    }
    
    private static String GetAccessTokenValue() throws ExampleException{
    	if(UrlTokenManager.accessTokenModel==null||UrlTokenManager.accessTokenModel.getTokenValue()==null||UrlTokenManager.accessTokenModel.getTokenValue().length()==0)		
    		throw new ExampleException(1000,"AccessTokenValue is null or empty.","AccessTokenValue is null or empty.");
    	else
    		return UrlTokenManager.accessTokenModel.getTokenValue();
    }
    
    public static String GetAccessTokenParamsString(Bundle Params) throws UnsupportedEncodingException, ExampleException{
    	Params.putString("tokenValue", GetAccessTokenValue());
    	return GetParamsString(Params);
    }
    
    public static String GetAccessTokenParamsJsonParmsString(Bundle Params) throws UnsupportedEncodingException, JSONException, ExampleException{	
    	Params.putString("tokenValue", GetAccessTokenValue());
    	return JsonParmsString(Params);
    }
    
	public static String GetParamsString(Bundle Params) throws UnsupportedEncodingException {
        if (Params == null)
            return "";
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String key : Params.keySet()) {
            if (first)
                first = false;
            else
                sb.append("&");
            sb.append(key + "=" + URLEncoder.encode(Params.getString(key),"utf8"));
        }
        return sb.toString();
    }
	
	public static String JsonParmsString(Bundle Params) throws UnsupportedEncodingException, JSONException{
		if (Params == null)
            return "";
		JSONObject jsonParms = new JSONObject();
		for (String key : Params.keySet()) {
			jsonParms.put(key, URLEncoder.encode(Params.getString(key),"utf8"));
        }
		return jsonParms.toString();
	}
	
	public static ExampleException PaeseExampleException(String exceptionString){
		GsonBuilder gsonBuilder = new GsonBuilder();  
        //gsonBuilder.registerTypeAdapter(VerifyResult.class, new VerifyResultSDerializer());  
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer()); 
        Gson gson = gsonBuilder.create();  
		return gson.fromJson(exceptionString, ExampleException.class);	
	}
    
}