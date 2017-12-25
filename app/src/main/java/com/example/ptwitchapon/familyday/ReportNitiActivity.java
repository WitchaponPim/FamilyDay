package com.example.ptwitchapon.familyday;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ptwitchapon.familyday.API.CallbackListener;
import com.example.ptwitchapon.familyday.API.ConnectionManager;
import com.squareup.okhttp.ResponseBody;

import retrofit.Retrofit;

public class ReportNitiActivity extends AppCompatActivity {
    String TAG = "Test" ;
    TextView total;
    ConnectionManager connect = new ConnectionManager();
    ProgressDialog loadingDialog;
    CallbackListener callbackListener;
    String totals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_niti);

        loadingDialog = ProgressDialog.show(this, "แปปนึง!!!!", "เด๋ว!!!!!", true, false);
        total = (TextView) findViewById(R.id.total);

        callbackListener = new CallbackListener() {

            @Override
            public void onResponse(String result, Retrofit retrofit) {
                totals = result+" คน";
                Log.d(TAG, totals);
                total.setText(totals);
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG,t.toString());
            }

            @Override
            public void onBodyError(ResponseBody responseBody) {
                Log.d(TAG,responseBody.toString());
            }

            @Override
            public void onBodyErrorIsNull() {
                Log.d(TAG,"null kUY!!!!!");
            }
        };


        connect.callTotal(callbackListener);
    }
}
