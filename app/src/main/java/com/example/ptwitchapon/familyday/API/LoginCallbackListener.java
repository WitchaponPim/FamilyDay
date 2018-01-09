package com.example.ptwitchapon.familyday.API;

import com.example.ptwitchapon.familyday.Model.UserModel;
import com.squareup.okhttp.ResponseBody;

import retrofit.Retrofit;

/**
 * Created by ptwitchapon on 8/1/2561.
 */
public interface LoginCallbackListener {
    public void onResponse(UserModel userModel, Retrofit retrofit);
    public void onFailure(Throwable t);
    public void onBodyError(ResponseBody responseBody);
    public void onBodyErrorIsNull();
}
