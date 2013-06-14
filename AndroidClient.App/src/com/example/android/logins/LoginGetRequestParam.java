package com.example.android.logins;

import android.os.Bundle;
import com.example.android.commons.RequestParam;
import com.example.android.exceptions.ExampleException;

public class LoginGetRequestParam extends RequestParam {
	
	private static final String APPKEY = "635022223931350076";
	private static final String APPSECRET = "4166551e59857a2b88b168f92115a4e1";

	private String USERNAME;
	private String PASSWORD;
	
	public LoginGetRequestParam (String userName, String passWord) {
		this.USERNAME = userName;
		this.PASSWORD = passWord;
	}

	public String getUserName() {
		return USERNAME;
	}

	public void setUserName(String userName) {
		this.USERNAME = userName;
	}

	public String getPassWord() {
		return PASSWORD;
	}

	public void setPassWord(String passWord) {
		this.PASSWORD = passWord;
	}

	@Override
	public Bundle getParams() throws ExampleException {
		Bundle parameters = new Bundle ();
		parameters.putString("appKey", APPKEY);
		parameters.putString("appSecret", APPSECRET);
		parameters.putString("userName", USERNAME);
		parameters.putString("passWord", PASSWORD);
		return parameters;
	}

}