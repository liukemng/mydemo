package com.example.android.commons;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import com.example.android.accounts.AccountGetRequestParam;
import com.example.android.accounts.AccountGetResponseBean;
import com.example.android.accounts.AccountHelper;
import com.example.android.logins.LoginGetRequestParam;
import com.example.android.logins.LoginGetResponseBean;
import com.example.android.logins.LoginHelper;

public class AsyncRequest {

	private Executor pool;

    public AsyncRequest() {
        this.pool = Executors.newFixedThreadPool(2);
    }

	public void getAccessTokenModel (LoginGetRequestParam param, RequestListener<LoginGetResponseBean> listener) {
		new LoginHelper().asyncGetAccessToken(pool, param, listener);
	}
	
	public void getAccountModel (AccountGetRequestParam param, RequestListener<AccountGetResponseBean> listener) {
		new AccountHelper().asyncGetAccountModel(pool, param, listener);
	}
}