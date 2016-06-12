package com.zjh.pureretrofit.http;

import android.content.Context;

import rx.Subscription;

/**
 * Created by zjh on 2016/6/10.
 *
 */
public abstract class HttpCallback<T> {
	protected Context context;


	public HttpCallback(Context context) {
		this.context = context;
	}

	public void onStart(Subscription subscription) {
	}

	public void onError(String message) {
	}

	public void onCancel() {
	}

	public abstract void onSuccess(T result);

	public void onEnd() {
	}
}
