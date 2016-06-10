package com.zjh.zjhretrofit.http;

import com.google.gson.annotations.Expose;

/**
 * Created by zjh on 2016/6/10.
 *
 */
public class HttpResult {
	@Expose
	private int status;
	@Expose
	private String message;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
