package com.example.ptwitchapon.familyday.API;

import com.example.ptwitchapon.familyday.Model.RepNitiModel;
import com.example.ptwitchapon.familyday.Model.ReportAllModel;
import com.example.ptwitchapon.familyday.Model.UserModel;
import com.squareup.okhttp.ResponseBody;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by ptwitchapon on 25/12/2560.
 */

public class ConnectionManager {
    String API = "http://familyday.lpn.co.th/familyday_dev/familyday/api/";
    public ConnectionManager() {

    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIService con = retrofit.create(APIService.class);

    public void callTotal(final CallbackListener listener){
        Call call = con.gettotaluser();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
               String result = response.body();
                if (result == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        listener.onBodyError(responseBody);
                    } else {
                        listener.onBodyErrorIsNull();
                    }
                } else {
                    //200
                    listener.onResponse( result, retrofit);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                    listener.onFailure(t);
            }
        });
    }

    public void login(final LoginCallbackListener listener,String user , String pass ){
        Call call = con.postLogin(user,pass);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Response<UserModel> response, Retrofit retrofit) {
                UserModel user = response.body();
                if (user == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        listener.onBodyError(responseBody);
                    } else {
                        listener.onBodyErrorIsNull();
                    }
                } else {
                    //200
                    listener.onResponse( user, retrofit);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onFailure(t);
            }
        });

    }

    public void reportall(final ReportAllCallbackListener listener){
        Call call = con.getReportAll();
        call.enqueue(new Callback<List<ReportAllModel>>() {
            @Override
            public void onResponse(Response<List<ReportAllModel>> response, Retrofit retrofit) {
                List<ReportAllModel> report_a =  response.body();
                if (report_a == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        listener.onBodyError(responseBody);
                    } else {
                        listener.onBodyErrorIsNull();
                    }
                } else {
                    //200
                    listener.onResponse( report_a, retrofit);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    public void repByniti(final RepNitiCallbackListener listener){
        Call call = con.getRepNiti();
        call.enqueue(new Callback<List<RepNitiModel>>() {
            @Override
            public void onResponse(Response<List<RepNitiModel>> response, Retrofit retrofit) {
                List<RepNitiModel> report_niti =  response.body();
                if (report_niti == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        listener.onBodyError(responseBody);
                    } else {
                        listener.onBodyErrorIsNull();
                    }
                } else {
                    //200
                    listener.onResponse( report_niti, retrofit);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}
