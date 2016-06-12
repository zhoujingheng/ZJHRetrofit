package com.zjh.pureretrofit.http;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Response;

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

    protected abstract Call<T> initCall() throws Exception;

    /**
     * 异步调用网络
     */
    public void asyncExecute(final HttpCallback<T> callback) {
        this.callback = callback;

        try {
            Call<T> call = initCall();
            if (call == null) {
                throw new NullPointerException("call can't not be null");
            }

            // 回调开始
//            if (callback != null) {
//                callback.onStart(subscription);
//            }

            //执行请求
            call.enqueue(new Callback());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 同步调用网络,不能再UI线程之间使用
     */
    public void syncExecute(){
        try {
            Call<T> call = initCall();
            if (call == null) {
                throw new NullPointerException("call can't not be null");
            }

            //执行请求
            call.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程回调
     */
    class Callback implements retrofit2.Callback<T>{

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            T t = response.body();
            callback.onSuccess(t);
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            String error = t.getMessage();
            callback.onError(error);
        }
    }

}
