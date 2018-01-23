package com.example.ptwitchapon.familyday.API;

import com.example.ptwitchapon.familyday.Model.RegisModel;
import com.example.ptwitchapon.familyday.Model.RepNitiModel;
import com.example.ptwitchapon.familyday.Model.ReportAllModel;
import com.example.ptwitchapon.familyday.Model.Report_allModel;
import com.example.ptwitchapon.familyday.Model.SaveModel;
import com.example.ptwitchapon.familyday.Model.UserModel;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by ptwitchapon on 25/12/2560.
 */

public interface APIService {
    @GET("getNumOfEntry")
    Call<Report_allModel> gettotaluser();
    @FormUrlEncoded
    @POST("mlogin")
    Call<UserModel> postLogin(@Field("username") String user, @Field("password") String pass);
    @GET("summary")
    Call<List<ReportAllModel>> getReportAll();
    @GET("rep_niti")
    Call<List<RepNitiModel>> getRepNiti();
    @FormUrlEncoded
    @POST("qrChk")
    Call<RegisModel> scan_QR(@Field("qrcode")String qr,@Field("username") String user,@Field("activity") String act_code);
    @FormUrlEncoded
    @POST("change_run")
    Call<String> changRun(@Field("qrcode")String qr);

    @FormUrlEncoded
    @POST("saveActivity")
    Call<List<SaveModel>> save_QR(@Field("qrcode")String qr, @Field("username") String user, @Field("activity") String act_code);


}
