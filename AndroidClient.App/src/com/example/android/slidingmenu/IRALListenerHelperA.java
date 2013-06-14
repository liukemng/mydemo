package com.example.android.slidingmenu;

import java.util.ArrayList;
import android.widget.Toast;
import com.example.android.accounts.AccountGetRequestParam;
import com.example.android.accounts.AccountGetResponseBean;
import com.example.android.commons.AsyncRequest;
import com.example.android.commons.RequestListener;
import com.example.android.exceptions.ExampleException;
import com.example.android.models.TestMessageModel;
import com.example.android.refreshandload.IRefreshAndLoadListener;

public class IRALListenerHelperA {
	
	public static IRefreshAndLoadListener getIRALL1(final ContentFragmentA cfa){
		
		return new IRefreshAndLoadListener(){
		
			@Override
			public void onLoad() {
				if (cfa.sfContext.getGeneralRequest() != null) {
					AsyncRequest asyncRequest = new AsyncRequest();
					AccountGetRequestParam param= new AccountGetRequestParam();
					RequestListener<AccountGetResponseBean> listener = new RequestListener<AccountGetResponseBean>() {

						@Override
						public void onComplete(final AccountGetResponseBean bean) {
							cfa.sfContext.runOnUiThread(new Runnable() {			
								@Override
								public void run() {
									cfa.rllv1LoadData(new ArrayList<TestMessageModel>());														
								}
							});
						}

						@Override
						public void onExampleException(final ExampleException exampleException) {
							cfa.sfContext.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(cfa.sfContext, exampleException.getErrorMessage(), Toast.LENGTH_LONG).show();
									cfa.rllv1LoadData(null);	
								}
							});
						}

						@Override
						public void onFault(final Throwable fault) {
							cfa.sfContext.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(cfa.sfContext, fault.getMessage(), Toast.LENGTH_LONG).show();
									cfa.rllv1LoadData(null);
								}
							});
						}

					};
					asyncRequest.getAccountModel(param, listener);
				}
			}			
			
			@Override
			public void onRefresh() {
				if (cfa.sfContext.getGeneralRequest() != null) {
					AsyncRequest asyncRequest = new AsyncRequest();
					AccountGetRequestParam param= new AccountGetRequestParam();
					RequestListener<AccountGetResponseBean> listener = new RequestListener<AccountGetResponseBean>() {

						@Override
						public void onComplete(final AccountGetResponseBean bean) {
							cfa.sfContext.runOnUiThread(new Runnable() {			
								@Override
								public void run() {
									cfa.rllv1RefreshData(new ArrayList<TestMessageModel>());
								}
							});
						}

						@Override
						public void onExampleException(final ExampleException exampleException) {
							cfa.sfContext.runOnUiThread(new Runnable() {		
								@Override
								public void run() {
									Toast.makeText(cfa.sfContext, exampleException.getErrorMessage(), Toast.LENGTH_LONG).show();
									cfa.rllv1RefreshData(null);
								}
							});
						}

						@Override
						public void onFault(final Throwable fault) {
							cfa.sfContext.runOnUiThread(new Runnable() {		
								@Override
								public void run() {							
									Toast.makeText(cfa.sfContext, fault.getMessage(), Toast.LENGTH_LONG).show();
									cfa.rllv1RefreshData(null);
								}
							});
						}

					};
					asyncRequest.getAccountModel(param, listener);
				}
			}

		};
		
	}
	
	public static IRefreshAndLoadListener getIRALL2(final ContentFragmentA cfa){
		
		return new IRefreshAndLoadListener(){
		
			@Override
			public void onLoad() {
				if (cfa.sfContext.getGeneralRequest() != null) {
					AsyncRequest asyncRequest = new AsyncRequest();
					AccountGetRequestParam param= new AccountGetRequestParam();
					RequestListener<AccountGetResponseBean> listener = new RequestListener<AccountGetResponseBean>() {

						@Override
						public void onComplete(final AccountGetResponseBean bean) {
							cfa.sfContext.runOnUiThread(new Runnable() {			
								@Override
								public void run() {
									cfa.rllv2LoadData(new ArrayList<TestMessageModel>());														
								}
							});
						}

						@Override
						public void onExampleException(final ExampleException exampleException) {
							cfa.sfContext.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(cfa.sfContext, exampleException.getErrorMessage(), Toast.LENGTH_LONG).show();
									cfa.rllv2LoadData(null);	
								}
							});
						}

						@Override
						public void onFault(final Throwable fault) {
							cfa.sfContext.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(cfa.sfContext, fault.getMessage(), Toast.LENGTH_LONG).show();
									cfa.rllv2LoadData(null);
								}
							});
						}

					};
					asyncRequest.getAccountModel(param, listener);
				}
			}			
			
			@Override
			public void onRefresh() {
				if (cfa.sfContext.getGeneralRequest() != null) {
					AsyncRequest asyncRequest = new AsyncRequest();
					AccountGetRequestParam param= new AccountGetRequestParam();
					RequestListener<AccountGetResponseBean> listener = new RequestListener<AccountGetResponseBean>() {

						@Override
						public void onComplete(final AccountGetResponseBean bean) {
							cfa.sfContext.runOnUiThread(new Runnable() {			
								@Override
								public void run() {
									cfa.rllv2RefreshData(new ArrayList<TestMessageModel>());
								}
							});
						}

						@Override
						public void onExampleException(final ExampleException exampleException) {
							cfa.sfContext.runOnUiThread(new Runnable() {		
								@Override
								public void run() {
									Toast.makeText(cfa.sfContext, exampleException.getErrorMessage(), Toast.LENGTH_LONG).show();
									cfa.rllv2RefreshData(null);
								}
							});
						}

						@Override
						public void onFault(final Throwable fault) {
							cfa.sfContext.runOnUiThread(new Runnable() {		
								@Override
								public void run() {							
									Toast.makeText(cfa.sfContext, fault.getMessage(), Toast.LENGTH_LONG).show();
									cfa.rllv2RefreshData(null);
								}
							});
						}

					};
					asyncRequest.getAccountModel(param, listener);
				}
			}

		};
		
	}
	
	public static IRefreshAndLoadListener getIRALL3(final ContentFragmentA cfa){
		
		return new IRefreshAndLoadListener(){
		
			@Override
			public void onLoad() {
				if (cfa.sfContext.getGeneralRequest() != null) {
					AsyncRequest asyncRequest = new AsyncRequest();
					AccountGetRequestParam param= new AccountGetRequestParam();
					RequestListener<AccountGetResponseBean> listener = new RequestListener<AccountGetResponseBean>() {

						@Override
						public void onComplete(final AccountGetResponseBean bean) {
							cfa.sfContext.runOnUiThread(new Runnable() {			
								@Override
								public void run() {
									cfa.rllv3LoadData(new ArrayList<TestMessageModel>());														
								}
							});
						}

						@Override
						public void onExampleException(final ExampleException exampleException) {
							cfa.sfContext.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(cfa.sfContext, exampleException.getErrorMessage(), Toast.LENGTH_LONG).show();
									cfa.rllv3LoadData(null);	
								}
							});
						}

						@Override
						public void onFault(final Throwable fault) {
							cfa.sfContext.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(cfa.sfContext, fault.getMessage(), Toast.LENGTH_LONG).show();
									cfa.rllv3LoadData(null);
								}
							});
						}

					};
					asyncRequest.getAccountModel(param, listener);
				}
			}			
			
			@Override
			public void onRefresh() {
				if (cfa.sfContext.getGeneralRequest() != null) {
					AsyncRequest asyncRequest = new AsyncRequest();
					AccountGetRequestParam param= new AccountGetRequestParam();
					RequestListener<AccountGetResponseBean> listener = new RequestListener<AccountGetResponseBean>() {

						@Override
						public void onComplete(final AccountGetResponseBean bean) {
							cfa.sfContext.runOnUiThread(new Runnable() {			
								@Override
								public void run() {
									cfa.rllv3RefreshData(new ArrayList<TestMessageModel>());
								}
							});
						}

						@Override
						public void onExampleException(final ExampleException exampleException) {
							cfa.sfContext.runOnUiThread(new Runnable() {		
								@Override
								public void run() {
									Toast.makeText(cfa.sfContext, exampleException.getErrorMessage(), Toast.LENGTH_LONG).show();
									cfa.rllv3RefreshData(null);
								}
							});
						}

						@Override
						public void onFault(final Throwable fault) {
							cfa.sfContext.runOnUiThread(new Runnable() {		
								@Override
								public void run() {							
									Toast.makeText(cfa.sfContext, fault.getMessage(), Toast.LENGTH_LONG).show();
									cfa.rllv3RefreshData(null);
								}
							});
						}

					};
					asyncRequest.getAccountModel(param, listener);
				}
			}

		};
		
	}
	
	public static IRefreshAndLoadListener getIRALL4(final ContentFragmentA cfa){
		
		return new IRefreshAndLoadListener(){
		
			@Override
			public void onLoad() {
				if (cfa.sfContext.getGeneralRequest() != null) {
					AsyncRequest asyncRequest = new AsyncRequest();
					AccountGetRequestParam param= new AccountGetRequestParam();
					RequestListener<AccountGetResponseBean> listener = new RequestListener<AccountGetResponseBean>() {

						@Override
						public void onComplete(final AccountGetResponseBean bean) {
							cfa.sfContext.runOnUiThread(new Runnable() {			
								@Override
								public void run() {
									cfa.rllv4LoadData(new ArrayList<TestMessageModel>());														
								}
							});
						}

						@Override
						public void onExampleException(final ExampleException exampleException) {
							cfa.sfContext.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(cfa.sfContext, exampleException.getErrorMessage(), Toast.LENGTH_LONG).show();
									cfa.rllv4LoadData(null);	
								}
							});
						}

						@Override
						public void onFault(final Throwable fault) {
							cfa.sfContext.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(cfa.sfContext, fault.getMessage(), Toast.LENGTH_LONG).show();
									cfa.rllv4LoadData(null);
								}
							});
						}

					};
					asyncRequest.getAccountModel(param, listener);
				}
			}			
			
			@Override
			public void onRefresh() {
				if (cfa.sfContext.getGeneralRequest() != null) {
					AsyncRequest asyncRequest = new AsyncRequest();
					AccountGetRequestParam param= new AccountGetRequestParam();
					RequestListener<AccountGetResponseBean> listener = new RequestListener<AccountGetResponseBean>() {

						@Override
						public void onComplete(final AccountGetResponseBean bean) {
							cfa.sfContext.runOnUiThread(new Runnable() {			
								@Override
								public void run() {
									cfa.rllv4RefreshData(new ArrayList<TestMessageModel>());
								}
							});
						}

						@Override
						public void onExampleException(final ExampleException exampleException) {
							cfa.sfContext.runOnUiThread(new Runnable() {		
								@Override
								public void run() {
									Toast.makeText(cfa.sfContext, exampleException.getErrorMessage(), Toast.LENGTH_LONG).show();
									cfa.rllv4RefreshData(null);
								}
							});
						}

						@Override
						public void onFault(final Throwable fault) {
							cfa.sfContext.runOnUiThread(new Runnable() {		
								@Override
								public void run() {							
									Toast.makeText(cfa.sfContext, fault.getMessage(), Toast.LENGTH_LONG).show();
									cfa.rllv4RefreshData(null);
								}
							});
						}

					};
					asyncRequest.getAccountModel(param, listener);
				}
			}

		};
		
	}	
	
}