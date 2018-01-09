package com.example.ptwitchapon.familyday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.ListView;

import android.widget.TextView;

import com.example.ptwitchapon.familyday.API.ConnectionManager;
import com.example.ptwitchapon.familyday.API.RepNitiCallbackListener;
import com.example.ptwitchapon.familyday.API.ReportAllCallbackListener;
import com.example.ptwitchapon.familyday.Adapter.RepNitiAdapter;
import com.example.ptwitchapon.familyday.Model.RepNitiModel;
import com.squareup.okhttp.ResponseBody;

import java.util.List;

import retrofit.Retrofit;

public class ReportByNitiActivity extends AppCompatActivity {
    String TAG = "Poon";
    ListView reportniti;
    int totals;
    SearchView search;
    TextView total;
    RepNitiAdapter adapter;
    RepNitiCallbackListener repNitiCallbackListener;
    ConnectionManager connect = new ConnectionManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_by_niti);
        reportniti = (ListView) findViewById(R.id.listreportniti);
        search = (SearchView) findViewById(R.id.search);

        repNitiCallbackListener = new RepNitiCallbackListener() {
            @Override
            public void onResponse(List<RepNitiModel> repNitiModels, Retrofit retrofit) {
                Utils.repNitiModels = repNitiModels;
                Log.d(TAG, "onResponse: "+repNitiModels.get(0).getNT_TNAME().toString());
                setadapter();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: ");
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
        connect.repByniti(repNitiCallbackListener);
    }

    private void setadapter(){

        adapter = new RepNitiAdapter(getApplicationContext(),Utils.repNitiModels);
        reportniti.setAdapter(adapter);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }
}
