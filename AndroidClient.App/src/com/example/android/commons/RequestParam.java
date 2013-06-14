package com.example.android.commons;

import android.os.Bundle;
import android.text.TextUtils;
import com.example.android.exceptions.ExampleException;

public abstract class RequestParam {
	
	public abstract Bundle getParams() throws ExampleException;
	
	public void checkNullParams (String... params) throws ExampleException {
		
		for (String param : params) {
			if (TextUtils.isEmpty(param)) {
				String errorMsg = "required parameter MUST NOT be null.";
				throw new ExampleException(1, errorMsg, errorMsg);
			}
		}
		
	}
}