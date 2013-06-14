package com.example.android.messages;

import java.util.concurrent.Executor;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import android.os.Bundle;
import com.example.android.commons.RequestListener;
import com.example.android.commons.GeneralRequest;
import com.example.android.commons.UrlTokenManager;
import com.example.android.commons.Util;
import com.example.android.exceptions.ExampleException;

public class MessageHelper{
	
	public MessageListGetResponseBean getResponseBean(MessageListGetRequestParam param) throws ExampleException, Throwable {
		Bundle parameters = param.getParams();
		MessageListGetResponseBean responseBean = null;
		try {
			HttpResponse response = GeneralRequest.AccessTokenGetRequest(UrlTokenManager.messageUrl, parameters);
			if(response.getStatusLine().getStatusCode()==200)//执行正确
			{
				responseBean = new MessageListGetResponseBean(EntityUtils.toString(response.getEntity()));
				return responseBean;
			}
			else//执行错误 StatusCode==400
				throw Util.PaeseExampleException(EntityUtils.toString(response.getEntity()));
		} catch (RuntimeException re) {
			Util.logger("runtime exception" + re.getMessage());
			throw new Throwable(re);
		}
	}
	
	public void asyncGetTestMessageModels(Executor pool, final MessageListGetRequestParam param, final RequestListener<MessageListGetResponseBean> listener) {	
		pool.execute(new Runnable() {		
			@Override
			public void run() {	
				try {
					MessageListGetResponseBean bean = getResponseBean(param);
					if (listener != null)
						listener.onComplete(bean);
				} catch (ExampleException e) {
					Util.logger("example exception " + e.getMessage());
					if (listener != null)
						listener.onExampleException(e);
				} catch (Throwable e) {
					Util.logger("on fault " + e.getMessage());
					if (listener != null)
						listener.onFault(e);
				}			
			}
		});	
	}
	
}