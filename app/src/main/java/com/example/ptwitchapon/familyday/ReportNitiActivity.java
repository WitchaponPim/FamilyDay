package com.example.ptwitchapon.familyday;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ptwitchapon.familyday.API.CallbackListener;
import com.example.ptwitchapon.familyday.API.ConnectionManager;
import com.example.ptwitchapon.familyday.Model.Report_allModel;
import com.squareup.okhttp.ResponseBody;

import java.text.DecimalFormat;

import retrofit.Retrofit;

public class ReportNitiActivity extends AppCompatActivity {
    String TAG = "Test";
    TextView total,sum,percent;
    ConnectionManager connect = new ConnectionManager();
    ProgressDialog loadingDialog;
    CallbackListener callbackListener;
    Report_allModel report_allModel;
//    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_niti);
        callbackListener = new CallbackListener() {
            @Override
            public void onResponse(Report_allModel result, Retrofit retrofit) {
                report_allModel = result;
                Log.d(TAG, report_allModel.getSUM()+" / "+ report_allModel.getTOTAL());
                double per = Double.valueOf(report_allModel.getSUM());
                double p = Double.valueOf(report_allModel.getTOTAL());
                double totals = per/p;
                totals = totals*100;

                sum.setText(report_allModel.getSUM());
                total.setText(report_allModel.getTOTAL());
                percent.setText(String.valueOf(new DecimalFormat("##").format(totals))+" %");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
            }

            @Override
            public void onBodyError(ResponseBody responseBody) {
                Log.d(TAG, "onBodyError: "+responseBody.toString());
            }

            @Override
            public void onBodyErrorIsNull() {
                Log.d(TAG, "null kUY!!!!!");
            }
        };

        total = (TextView) findViewById(R.id.total);
        sum = (TextView) findViewById(R.id.sum) ;
        percent = (TextView) findViewById(R.id.sumper) ;
//        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
//
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                connect.callTotal(callbackListener);
//                onRefreshcomplete();
//            }
//        });

        connect.callTotal(callbackListener);
    }

    private void onRefreshcomplete() {
//        swipeRefreshLayout.setRefreshing(false);
    }


}
