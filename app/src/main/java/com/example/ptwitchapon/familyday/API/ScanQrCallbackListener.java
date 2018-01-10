package com.example.ptwitchapon.familyday.API;

import com.example.ptwitchapon.familyday.Model.RegisModel;
import com.example.ptwitchapon.familyday.Model.ReportAllModel;
import com.squareup.okhttp.ResponseBody;

import java.util.List;

import retrofit.Retrofit;

/**
 * Created by ptwitchapon on 10/1/2561.
 */

public interface ScanQrCallbackListener {
    public void onResponse(RegisModel regisModel, Retrofit retrofit);
    public void onFailure(Throwable t);
    public void onBodyError(ResponseBody responseBody);
    public void onBodyErrorIsNull();
}
