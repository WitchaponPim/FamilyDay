package com.example.ptwitchapon.familyday.API;

import com.example.ptwitchapon.familyday.Model.Report_allModel;
import com.squareup.okhttp.ResponseBody;

import retrofit.Retrofit;

/**
 * Created by ptwitchapon on 25/12/2560.
 */

public interface CallbackListener {
    public void onResponse(Report_allModel result, Retrofit retrofit);
    public void onFailure(Throwable t);
    public void onBodyError(ResponseBody responseBody);
    public void onBodyErrorIsNull();
}
