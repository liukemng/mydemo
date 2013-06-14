package com.example.android.messages;

import java.util.Date;
import java.util.List;
import com.example.android.commons.ResponseBean;
import com.example.android.extensions.DateDeserializer;
import com.example.android.models.TestMessageModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class MessageListGetResponseBean extends ResponseBean {

	private List<TestMessageModel> testMessageModels;
	
	public MessageListGetResponseBean(String response) {
		super(response);
		// TODO Auto-generated constructor stub
		if (response == null) {
			return;
		}
        
        try {        	
        	GsonBuilder gsonBuilder = new GsonBuilder();  
	        //gsonBuilder.registerTypeAdapter(VerifyResult.class, new VerifyResultSDerializer());  
	        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer()); 
	        Gson gson = gsonBuilder.create(); 
	        testMessageModels=gson.fromJson(response, new TypeToken<List<TestMessageModel>>(){}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public List<TestMessageModel> getTestMessageModels() {
		return testMessageModels;
	}

	public void setTestMessageModels(List<TestMessageModel> testMessageModels) {
		this.testMessageModels = testMessageModels;
	}
	
}