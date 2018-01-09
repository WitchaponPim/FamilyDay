package com.example.ptwitchapon.familyday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ptwitchapon.familyday.API.ConnectionManager;
import com.example.ptwitchapon.familyday.API.ReportAllCallbackListener;
import com.example.ptwitchapon.familyday.Adapter.ReportAdapter;
import com.example.ptwitchapon.familyday.Model.ReportAllModel;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.internal.Util;

import java.util.List;

import retrofit.Retrofit;

public class ReportAllActivity extends AppCompatActivity {
    String TAG = "Poon";
    ListView report;
    int totals;
    TextView total;
    ReportAllCallbackListener reportAllCallbackListener;
    ConnectionManager connect = new ConnectionManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_all);
        report = (ListView) findViewById(R.id.listreport);
        total = (TextView) findViewById(R.id.totalall);
        reportAllCallbackListener = new ReportAllCallbackListener() {

            @Override
            public void onResponse(List<ReportAllModel> reportAllModel, Retrofit retrofit) {
                Utils.reportModel = reportAllModel;
                Log.d(TAG, "onResponse: "+reportAllModel.get(0).getEVENT().toString());
                setadapter();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
            }

            @Override
            public void onBodyError(ResponseBody responseBody) {
                Log.d(TAG, "onBodyError: ");
            }

            @Override
            public void onBodyErrorIsNull() {
                Log.d(TAG, "onBodyErrorIsNull: ");
            }
        };

        connect.reportall(reportAllCallbackListener);
    }

    private void setadapter(){
        ReportAdapter reportAdapter = new ReportAdapter(getApplicationContext(),Utils.reportModel);
        report.setAdapter(reportAdapter);
        Log.d(TAG, "setadapter: "+Utils.reportModel.size());
        for (int i = 0; i < Utils.reportModel.size(); i++) {
            totals = totals+Integer.valueOf(Utils.reportModel.get(i).getTOTAL());
        }
        total.setText(String.valueOf(totals));
    }
}
