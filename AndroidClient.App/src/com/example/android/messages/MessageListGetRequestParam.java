package com.example.android.messages;

import android.os.Bundle;
import com.example.android.commons.RequestParam;
import com.example.android.exceptions.ExampleException;

public class MessageListGetRequestParam extends RequestParam {

	@Override
	public Bundle getParams() throws ExampleException {
		Bundle parameters = new Bundle ();
		return parameters;
	}

}

