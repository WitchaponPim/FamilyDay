package com.example.ptwitchapon.familyday.API;

import com.example.ptwitchapon.familyday.Model.SaveModel;
import com.squareup.okhttp.ResponseBody;

import java.util.List;

import retrofit.Retrofit;

/**
 * Created by ptwitchapon on 23/1/2561.
 */

public interface SaveQrCallbackListener {
    public void onResponse(List<SaveModel> saveModels, Retrofit retrofit);
    public void onFailure(Throwable t);
    public void onBodyError(ResponseBody responseBody);
    public void onBodyErrorIsNull();
}
