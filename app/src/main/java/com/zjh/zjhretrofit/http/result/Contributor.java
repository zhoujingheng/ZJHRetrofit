package com.zjh.zjhretrofit.http.result;


/**
 * Created by zjh on 2016/6/10.
 *
 */
public class Contributor {
    public final String login;
    public final int contributions;

    public Contributor(String login, int contributions) {
        this.login = login;
        this.contributions = contributions;
    }
}
