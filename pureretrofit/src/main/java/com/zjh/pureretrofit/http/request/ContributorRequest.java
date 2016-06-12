package com.zjh.pureretrofit.http.request;

import android.content.Context;

import com.zjh.pureretrofit.http.GitHubService;
import com.zjh.pureretrofit.http.HttpRequest;
import com.zjh.pureretrofit.http.Response.Contributor;

import java.util.List;

import retrofit2.Call;

/**
 * Created by zjh on 2016/6/10.
 *
 */
public class ContributorRequest extends HttpRequest<List<Contributor>> {
    public ContributorRequest(Context context) {
        super(context);
    }

    @Override
    protected Call<List<Contributor>> initCall() throws Exception {
        return GitHubService.getService().
                contributors("square", "retrofit");
    }
}
