package com.zjh.zjhretrofit.http;

import android.content.Context;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zjh on 2016/6/10.
 *
 */
public abstract class HttpRequest<T> {
    private HttpCallback<T> callback;
    protected Context context;

    public HttpRequest(Context context) {
        this.context = context;
    }

    protected abstract Observable<T> initObservable() throws Exception;

    /**
     * 异步调用网络
     */
    public void asyncExecute(final HttpCallback<T> callback) {
        this.callback = callback;

        try {
            Observable<T> observable = initObservable();
            if (observable == null) {
                throw new NullPointerException("Observable can't not be null");
            }

            //执行请求
            Subscription subscription = observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .subscribe(new Callback());

            // 回调开始
            if (callback != null) {
                callback.onStart(subscription);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 线程回调
     */
    class Callback extends Subscriber<T> {

        @Override
        public void onCompleted() {
            if (callback != null) {
                callback.onEnd();
            }
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
            if (callback != null) {
                String msg = throwable.getMessage();
                if (msg == null || msg.length() == 0) {
                    callback.onError("没有网络");
                } else {
                    callback.onError(throwable.getMessage());
                }
            }
        }

        @Override
        public void onNext(T result) {
            if (callback != null) {
                callback.onSuccess(result);
            }
        }
    }

}
