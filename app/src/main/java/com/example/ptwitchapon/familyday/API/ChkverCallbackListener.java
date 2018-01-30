package com.example.ptwitchapon.familyday.API;

import com.example.ptwitchapon.familyday.Model.ChkversionModel;
import com.example.ptwitchapon.familyday.Model.VerModel;
import com.squareup.okhttp.ResponseBody;

import retrofit.Retrofit;

/**
 * Created by ptwitchapon on 30/1/2561.
 */

public interface ChkverCallbackListener {
    public void onResponse(VerModel chkversionModel, Retrofit retrofit);
    public void onFailure(Throwable t);
    public void onBodyError(ResponseBody responseBody);
    public void onBodyErrorIsNull();
}
