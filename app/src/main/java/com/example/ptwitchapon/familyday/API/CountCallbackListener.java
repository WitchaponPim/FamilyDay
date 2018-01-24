package com.example.ptwitchapon.familyday.API;

import com.squareup.okhttp.ResponseBody;

import retrofit.Retrofit;

/**
 * Created by ptwitchapon on 24/1/2561.
 */

public interface CountCallbackListener {
    public void onResponse(String result, Retrofit retrofit);
    public void onFailure(Throwable t);
    public void onBodyError(ResponseBody responseBody);
    public void onBodyErrorIsNull();
}
