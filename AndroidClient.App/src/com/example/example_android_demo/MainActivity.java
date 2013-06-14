package com.example.example_android_demo;

import com.example.android.accounts.AccountGetRequestParam;
import com.example.android.accounts.AccountGetResponseBean;
import com.example.android.commons.RequestListener;
import com.example.android.commons.AsyncRequest;
import com.example.android.commons.UrlTokenManager;
import com.example.android.exceptions.ExampleException;
import com.example.android.models.AccountModel;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button messagelistButton=(Button)findViewById(R.id.main_btn_messagelist);
		
		messagelistButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, TestSMActivity.class);
				
				startActivity(intent);//无返回值的调用,启动一个明确的activity
			}
		});
		
		final TextView tvwid=(TextView)findViewById(R.id.main_tvw_id);
		final TextView tvwusername=(TextView)findViewById(R.id.main_tvw_username);
		final TextView tvwpassword=(TextView)findViewById(R.id.main_tvw_password);
		final TextView tvwtoken=(TextView)findViewById(R.id.main_tvw_token);
		
		if (getGeneralRequest() != null) {
			AsyncRequest asyncRequest = new AsyncRequest();
			AccountGetRequestParam param= new AccountGetRequestParam();
			RequestListener<AccountGetResponseBean> listener = new RequestListener<AccountGetResponseBean>() {

				@Override
				public void onComplete(final AccountGetResponseBean bean) {
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							AccountModel model=bean.getAccountModel();
							tvwid.setText((Integer.valueOf(model.getId())).toString());
							tvwusername.setText(model.getUserName());
							tvwpassword.setText(model.getPassWord());
							tvwtoken.setText(UrlTokenManager.accessTokenModel.getTokenValue());
						}
					});
				}

				@Override
				public void onExampleException(final ExampleException exampleException) {
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, exampleException.getErrorMessage(), Toast.LENGTH_LONG).show();
							dismissProgress();
						}
					});
				}

				@Override
				public void onFault(final Throwable fault) {
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, fault.getMessage(), Toast.LENGTH_LONG).show();
							dismissProgress();
						}
					});
				}

			};
			asyncRequest.getAccountModel(param, listener);
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
