package com.example.ptwitchapon.familyday;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ptwitchapon.familyday.API.CallbackListener;
import com.example.ptwitchapon.familyday.API.ConnectionManager;
import com.example.ptwitchapon.familyday.Adapter.MenuAdapter;
import com.example.ptwitchapon.familyday.Model.Report_allModel;
import com.squareup.okhttp.ResponseBody;

import java.text.DecimalFormat;
import java.util.ArrayList;

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
        ListView listView = (ListView) findViewById(R.id.listView1);
        final ArrayList<String> list = new ArrayList<>();
        list.add("Monitor เดิน-วิ่ง");
        list.add("Monitor ธรรมมะในสวน");
        list.add("Monitor ดนตรีในสวน");
        list.add("Monitor เกมส์-คอนเสิร์ต");


        MenuAdapter adapter = new MenuAdapter(getApplicationContext(), list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ReportNitiActivity.this,ReportFromWebActivity.class);
                String link = null;
                switch (i) {
                    case 0:
                        link = "http://familyday.lpn.co.th/familyday/monitor/activity/run";
                        break;
                    case 1:
                        link = "http://familyday.lpn.co.th/familyday/monitor/activity/thamma";
                        break;
                    case 2:
                        link = "http://familyday.lpn.co.th/familyday/monitor/activity/music";
                        break;
                    case 3:
                        link = "http://familyday.lpn.co.th/familyday/monitor/activity/game";
                        break;
                }
                intent.putExtra("link",  link);
                startActivity(intent);

            }
        });

//        callbackListener = new CallbackListener() {
//            @Override
//            public void onResponse(Report_allModel result, Retrofit retrofit) {
//                report_allModel = result;
//                Log.d(TAG, report_allModel.getSUM()+" / "+ report_allModel.getTOTAL());
//                double per = Double.valueOf(report_allModel.getSUM());
//                double p = Double.valueOf(report_allModel.getTOTAL());
//                double totals = per/p;
//                totals = totals*100;
//
//                sum.setText(report_allModel.getSUM());
//                total.setText(report_allModel.getTOTAL());
//                percent.setText(String.valueOf(new DecimalFormat("##").format(totals))+" %");
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.d(TAG, "onFailure: "+t.toString());
//            }
//
//            @Override
//            public void onBodyError(ResponseBody responseBody) {
//                Log.d(TAG, "onBodyError: "+responseBody.toString());
//            }
//
//            @Override
//            public void onBodyErrorIsNull() {
//                Log.d(TAG, "null kUY!!!!!");
//            }
//        };



    }


}
