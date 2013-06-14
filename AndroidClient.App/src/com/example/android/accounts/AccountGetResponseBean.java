package com.example.android.accounts;

import java.util.Date;
import com.example.android.commons.ResponseBean;
import com.example.android.extensions.DateDeserializer;
import com.example.android.models.AccountModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AccountGetResponseBean extends ResponseBean {
	
	private AccountModel accountModel;

	public AccountGetResponseBean(String response) {
		super(response);
		if (response == null) {
			return;
		}
        
        try {        	
        	GsonBuilder gsonBuilder = new GsonBuilder();  
	        //gsonBuilder.registerTypeAdapter(VerifyResult.class, new VerifyResultSDerializer());  
	        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer()); 
	        Gson gson = gsonBuilder.create(); 
	        accountModel=gson.fromJson(response, AccountModel.class);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
		
	public AccountModel getAccountModel() {
		return accountModel;
	}

	public void setAccountModel(AccountModel accountModel) {
		this.accountModel = accountModel;
	}
	
}