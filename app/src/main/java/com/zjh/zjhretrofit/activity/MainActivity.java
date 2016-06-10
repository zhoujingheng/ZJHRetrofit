package com.zjh.zjhretrofit.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.zjh.zjhretrofit.R;
import com.zjh.zjhretrofit.http.HttpCallback;
import com.zjh.zjhretrofit.http.request.ContributorRequest;
import com.zjh.zjhretrofit.http.result.Contributor;

import java.util.List;

/**
 * Created by zjh on 2016/6/10.
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onGet(View view){
        ContributorRequest request = new ContributorRequest(this);
        request.asyncExecute(new ContributorRequestCallback(this));
    }

    public void onPost(View view){

    }

    public void onPut(View view){

    }

    public void onDelete(View view){

    }

    class ContributorRequestCallback extends HttpCallback<List<Contributor>>{

        public ContributorRequestCallback(Context context) {
            super(context);
        }

        @Override
        public void onSuccess(List<Contributor> result) {
            for (Contributor contributor : result) {
                System.out.println(contributor.login + " (" + contributor.contributions + ")");
            }
        }
    }

}
