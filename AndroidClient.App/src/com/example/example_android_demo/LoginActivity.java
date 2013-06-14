package com.example.example_android_demo;

import com.example.android.commons.RequestListener;
import com.example.android.commons.UrlTokenManager;
import com.example.android.commons.AsyncRequest;
import com.example.android.exceptions.ExampleException;
import com.example.android.logins.LoginGetRequestParam;
import com.example.android.logins.LoginGetResponseBean;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		/*final Intent intent = new Intent(this, BaseActivity.class);
    	intent.putExtra(Example.Example_LABEL, example);*/
		
		
		Button loginButton=(Button)findViewById(R.id.login_btn_login);
		
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				if (getGeneralRequest() != null) {
					AsyncRequest asyncRequest = new AsyncRequest();
					showProgress("登录中","请稍后...");
					LoginGetRequestParam param = new LoginGetRequestParam("11","11");
					RequestListener<LoginGetResponseBean> listener = new RequestListener<LoginGetResponseBean>() {

						@Override
						public void onComplete(final LoginGetResponseBean bean) {
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									dismissProgress();
									UrlTokenManager.accessTokenModel=bean.getAccessTokenModel();

									Intent intent = new Intent();
									intent.setClass(LoginActivity.this, MainActivity.class);
									startActivity(intent);//无返回值的调用,启动一个明确的activity
								}
							});
						}

						@Override
						public void onExampleException(final ExampleException exampleException) {
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									Toast.makeText(LoginActivity.this, exampleException.getErrorMessage(), Toast.LENGTH_LONG).show();
									dismissProgress();
								}
							});
						}

						@Override
						public void onFault(final Throwable fault) {
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									Toast.makeText(LoginActivity.this, fault.getMessage(), Toast.LENGTH_LONG).show();
									dismissProgress();
								}
							});
						}

					};
					asyncRequest.getAccessTokenModel(param, listener);
					
				}
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
