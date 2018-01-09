package com.example.ptwitchapon.familyday.API;

import com.example.ptwitchapon.familyday.Model.ReportAllModel;
import com.example.ptwitchapon.familyday.Model.UserModel;
import com.squareup.okhttp.ResponseBody;

import retrofit.Retrofit;

/**
 * Created by ptwitchapon on 9/1/2561.
 */

public interface ReportAllCallbackListener {
    public void onResponse(ReportAllModel reportAllModel, Retrofit retrofit);
    public void onFailure(Throwable t);
    public void onBodyError(ResponseBody responseBody);
    public void onBodyErrorIsNull();
}
