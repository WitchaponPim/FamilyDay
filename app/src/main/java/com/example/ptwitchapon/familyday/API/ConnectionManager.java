package com.example.ptwitchapon.familyday.API;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by ptwitchapon on 25/12/2560.
 */

public class ConnectionManager {
    String API = "http://familyday.lpn.co.th/familyday/api/";
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
}
