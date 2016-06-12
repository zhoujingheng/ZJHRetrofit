package com.zjh.pureretrofit.http;

import com.zjh.pureretrofit.http.Response.Contributor;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zjh on 2016/6/12.
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

            // Create a very simple REST adapter which points the GitHub API.
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            // Create an instance of our GitHub API interface.
            github = retrofit.create(GitHub.class);
        }

        return github;
    }

    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }
}
