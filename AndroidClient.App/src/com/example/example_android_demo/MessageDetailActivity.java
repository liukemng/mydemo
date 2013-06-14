package com.example.example_android_demo;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MessageDetailActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int position = 0;
		if (getIntent().getExtras() != null)
			position = getIntent().getExtras().getInt("position");
		
		setContentView(R.layout.activity_messagedetail);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		TextView contentTvw = (TextView)findViewById(R.id.textView1);
		contentTvw.setText("我是详细信息界面，position："+position);
				
		Button returnBtn = (Button)findViewById(R.id.button1);	
		returnBtn.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {

				MessageDetailActivity.this.finish();
				
			}
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
