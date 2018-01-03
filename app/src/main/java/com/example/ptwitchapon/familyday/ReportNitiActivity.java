package com.example.ptwitchapon.familyday;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ptwitchapon.familyday.API.CallbackListener;
import com.example.ptwitchapon.familyday.API.ConnectionManager;
import com.squareup.okhttp.ResponseBody;

import retrofit.Retrofit;

public class ReportNitiActivity extends AppCompatActivity {
    String TAG = "Test";
    TextView total;
    ConnectionManager connect = new ConnectionManager();
    ProgressDialog loadingDialog;
    CallbackListener callbackListener;
    String totals;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_niti);
        callbackListener = new CallbackListener() {
            @Override
            public void onResponse(String result, Retrofit retrofit) {
                totals = result + " คน";
                Log.d(TAG, totals);
                total.setText(totals);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, t.toString());
            }

            @Override
            public void onBodyError(ResponseBody responseBody) {
                Log.d(TAG, responseBody.toString());
            }

            @Override
            public void onBodyErrorIsNull() {
                Log.d(TAG, "null kUY!!!!!");
            }
        };

        total = (TextView) findViewById(R.id.total);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                connect.callTotal(callbackListener);
                onRefreshcomplete();
            }
        });

        connect.callTotal(callbackListener);
    }

    private void onRefreshcomplete() {
        swipeRefreshLayout.setRefreshing(false);
    }


}
