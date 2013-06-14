package com.example.android.logins;

import java.util.Date;
import com.example.android.commons.ResponseBean;
import com.example.android.extensions.DateDeserializer;
import com.example.android.models.AccessTokenModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LoginGetResponseBean extends ResponseBean {
	
	private AccessTokenModel accessTokenModel;

	public LoginGetResponseBean(String response) {
		super(response);
		if (response == null)
			return;
        
        try {        	
        	GsonBuilder gsonBuilder = new GsonBuilder();  
	        //gsonBuilder.registerTypeAdapter(VerifyResult.class, new VerifyResultSDerializer());  
	        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer()); 
	        Gson gson = gsonBuilder.create(); 
	        accessTokenModel=gson.fromJson(response, AccessTokenModel.class);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
		
	public AccessTokenModel getAccessTokenModel() {
		return accessTokenModel;
	}

	public void setAccessTokenModel(AccessTokenModel accessTokenModel) {
		this.accessTokenModel = accessTokenModel;
	}
	
}