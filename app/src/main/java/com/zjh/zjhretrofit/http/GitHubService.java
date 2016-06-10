package com.zjh.zjhretrofit.http;


import com.zjh.zjhretrofit.http.result.Contributor;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zjh on 2016/6/10.
 *
 */
public class GitHubService {
    public static final String API_URL = "https://api.github.com";
    static Retrofit retrofit;
    static GitHub github;

    private GitHubService() {
    }

    public synchronized static GitHub getService() {
        if (retrofit == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder().
                    addInterceptor(httpLoggingInterceptor).build();

            // Create a very simple REST adapter which points the GitHub API.
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            // Create an instance of our GitHub API interface.
            github = retrofit.create(GitHub.class);
        }

        return github;
    }

    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Observable<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }
}
