package com.example.example_android_demo;

import com.example.android.commons.GeneralRequest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends Activity {

	private ProgressDialog progressDialog;
	
	private GeneralRequest generalRequest;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		generalRequest = new GeneralRequest(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		dismissProgress();
	}
	
	public void showProgress() {
		showProgress("Please wait", "progressing");
	}

	public void showProgress(String title, String message) {
		progressDialog = ProgressDialog.show(this, title, message);
	}

	public void dismissProgress() {
		if (progressDialog != null) {
			try {
				progressDialog.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void showTip(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	public GeneralRequest getGeneralRequest(){
		return generalRequest;
	}

}