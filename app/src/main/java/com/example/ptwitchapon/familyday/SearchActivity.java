package com.example.ptwitchapon.familyday;

import android.content.Intent;
import android.icu.util.UniversalTimeScale;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.ptwitchapon.familyday.API.ConnectionManager;
import com.example.ptwitchapon.familyday.API.ScanQrCallbackListener;
import com.example.ptwitchapon.familyday.Adapter.RepNitiAdapter;
import com.example.ptwitchapon.familyday.Adapter.SearchAdapter;
import com.example.ptwitchapon.familyday.Model.RegisModel;
import com.squareup.okhttp.ResponseBody;

import retrofit.Retrofit;

public class SearchActivity extends AppCompatActivity{
    SearchView search;
    SearchAdapter adapter;
    RecyclerView list ;
    GridLayoutManager gridLayoutManager;
    ConnectionManager connect = new ConnectionManager();
    ScanQrCallbackListener scanQrCallbackListener ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        list = (RecyclerView) findViewById(R.id.list_search);
        search = (SearchView) findViewById(R.id.search);

        scanQrCallbackListener = new ScanQrCallbackListener() {
            @Override
            public void onResponse(RegisModel regisModel, Retrofit retrofit) {
                Utils.regisModel = regisModel;
                validate(Integer.valueOf(Utils.regisModel.getSTATUS_ID()));
            }

            @Override
            public void onFailure(Throwable t) {

            }

            @Override
            public void onBodyError(ResponseBody responseBody) {

            }

            @Override
            public void onBodyErrorIsNull() {

            }
        };

        setadapter();


    }

    private void setadapter(){
        adapter = new SearchAdapter(getApplicationContext(), Utils.regisModel.getPROFILE(), new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RegisModel.PROFILEBean profileBean, int position) {
                Utils.toast(SearchActivity.this,profileBean.getRG_SMS());
                connect.scanqr(scanQrCallbackListener,profileBean.getRG_SMS(),"","");
            }
        });

        list.setAdapter(adapter);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        list.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
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
    public void GogoConfirm(){
        Intent intent = new Intent(SearchActivity.this,ConfirmActivity.class);
        startActivity(intent);
    }


    public void validate(int status){
        switch (status) {
            case 1: GogoConfirm();
                break;
            case 3:
                    GogoConfirm();
                break;
            default:
                Toast.makeText(getApplicationContext(), Utils.regisModel.getSTATUS(), Toast.LENGTH_LONG).show();
                break;
        }
    }

}
