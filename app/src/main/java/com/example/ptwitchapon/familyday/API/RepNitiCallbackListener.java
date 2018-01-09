package com.example.ptwitchapon.familyday.API;

import com.example.ptwitchapon.familyday.Model.RepNitiModel;
import com.example.ptwitchapon.familyday.Model.ReportAllModel;
import com.squareup.okhttp.ResponseBody;

import java.util.List;

import retrofit.Retrofit;

/**
 * Created by ptwitchapon on 9/1/2561.
 */

public interface RepNitiCallbackListener {
    public void onResponse(List<RepNitiModel> repNitiModels, Retrofit retrofit);
    public void onFailure(Throwable t);
    public void onBodyError(ResponseBody responseBody);
    public void onBodyErrorIsNull();
}
