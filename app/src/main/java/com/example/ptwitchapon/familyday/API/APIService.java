package com.example.ptwitchapon.familyday.API;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by ptwitchapon on 25/12/2560.
 */

public interface APIService {
    @GET("getNumOfEntry")
    Call<String> gettotaluser();

}
