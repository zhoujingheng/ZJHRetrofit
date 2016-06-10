package com.zjh.zjhretrofit.http.request;

import android.content.Context;

import com.zjh.zjhretrofit.http.GitHubService;
import com.zjh.zjhretrofit.http.HttpRequest;
import com.zjh.zjhretrofit.http.result.Contributor;

import java.util.List;

import rx.Observable;

/**
 * Created by zjh on 2016/6/10.
 *
 */
public class ContributorRequest extends HttpRequest<List<Contributor>> {
    public ContributorRequest(Context context) {
        super(context);
    }

    @Override
    protected Observable<List<Contributor>> initObservable() throws Exception {
        Observable<List<Contributor>> observable = GitHubService.getService().
                contributors("square", "retrofit");
        return observable;
    }
}
