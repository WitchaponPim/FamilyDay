package com.example.ptwitchapon.familyday.API;

import com.example.ptwitchapon.familyday.Model.ChkversionModel;
import com.example.ptwitchapon.familyday.Model.RegisModel;
import com.example.ptwitchapon.familyday.Model.RepNitiModel;
import com.example.ptwitchapon.familyday.Model.ReportAllModel;
import com.example.ptwitchapon.familyday.Model.Report_allModel;
import com.example.ptwitchapon.familyday.Model.SaveModel;
import com.example.ptwitchapon.familyday.Model.UserModel;
import com.example.ptwitchapon.familyday.Model.VerModel;
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
//    String API = "http://familyday.lpn.co.th/familyday_dev/familyday/api/";
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
        call.enqueue(new Callback<Report_allModel>() {
            @Override
            public void onResponse(Response<Report_allModel> response, Retrofit retrofit) {
                Report_allModel result = response.body();
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

    public void scanqr(final ScanQrCallbackListener listener,String qr,String user,String act_code){
        Call call = con.scan_QR(qr,user,act_code);
        call.enqueue(new Callback<RegisModel>() {
            @Override
            public void onResponse(Response<RegisModel> response, Retrofit retrofit) {
                RegisModel regisModel =  response.body();
                if (regisModel == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        listener.onBodyError(responseBody);
                    } else {
                        listener.onBodyErrorIsNull();
                    }
                } else {
                    //200
                    listener.onResponse( regisModel, retrofit);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    public void changeRun(final ChangeCallbackListener listener,String qr){
        Call call = con.changRun(qr);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Response response, Retrofit retrofit) {
                String result = String.valueOf(response);
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

    public void saveqr(final SaveQrCallbackListener listener,String qr,String user ,String act_id){
        Call call = con.save_QR(qr,user,act_id);
        call.enqueue(new Callback<List<SaveModel>>() {
            @Override
            public void onResponse(Response<List<SaveModel>> response, Retrofit retrofit) {
                List<SaveModel> saveModel =  response.body();
                if (saveModel == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        listener.onBodyError(responseBody);
                    } else {
                        listener.onBodyErrorIsNull();
                    }
                } else {
                    //200
                    listener.onResponse( saveModel, retrofit);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    public void count(final CountCallbackListener listener,String count,String user,String act_code,String type){
        Call call = con.count_tamma(count,user,act_code,type);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Response response, Retrofit retrofit) {
                String result = String.valueOf(response);
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

    public void chkver(final ChkverCallbackListener listener,String ver){
        Call call = con.chkver(ver);
        call.enqueue(new Callback<VerModel>() {
            @Override
            public void onResponse(Response<VerModel> response, Retrofit retrofit) {
                VerModel chkversionModel = response.body();
                if (chkversionModel == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        listener.onBodyError(responseBody);
                    } else {
                        listener.onBodyErrorIsNull();
                    }
                } else {
                    //200
                    listener.onResponse( chkversionModel, retrofit);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onFailure(t);
            }
        });
    }

    public void calltamma(final CallbackListener listener){
        Call call = con.gettamma();
        call.enqueue(new Callback<Report_allModel>() {
            @Override
            public void onResponse(Response<Report_allModel> response, Retrofit retrofit) {
                Report_allModel result = response.body();
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

    public void regisNsave(final RegisNsaveCallbackListener listener,String qr,String user ,String act_id){
        Call call = con.regisNsave(qr,user,act_id);
        call.enqueue(new Callback<List<SaveModel>>() {
            @Override
            public void onResponse(Response<List<SaveModel>> response, Retrofit retrofit) {
                List<SaveModel> saveModel =  response.body();
                if (saveModel == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        listener.onBodyError(responseBody);
                    } else {
                        listener.onBodyErrorIsNull();
                    }
                } else {
                    //200
                    listener.onResponse( saveModel, retrofit);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}
