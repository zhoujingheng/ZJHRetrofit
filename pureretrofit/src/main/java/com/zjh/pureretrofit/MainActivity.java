package com.zjh.pureretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zjh.pureretrofit.http.GitHubService;
import com.zjh.pureretrofit.http.HttpCallback;
import com.zjh.pureretrofit.http.Response.Contributor;
import com.zjh.pureretrofit.http.request.ContributorRequest;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String API_URL = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onGet(View view){
        ContributorRequest request = new ContributorRequest(this);
        request.asyncExecute(new HttpCallback<List<Contributor>>(this) {
            @Override
            public void onSuccess(List<Contributor> result) {
                for (Contributor contributor : result) {
                    System.out.println(contributor.login + " (" + contributor.contributions + ")");
                }
            }
        });

    }

    public void onPost(View view){

    }

    public void onPut(View view){

    }

    public void onDelete(View view){

    }
}
