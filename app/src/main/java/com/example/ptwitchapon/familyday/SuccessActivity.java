package com.example.ptwitchapon.familyday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.example.ptwitchapon.familyday.API.ConnectionManager;
import com.example.ptwitchapon.familyday.API.ScanQrCallbackListener;
import com.example.ptwitchapon.familyday.Adapter.SearchAdapter;
import com.example.ptwitchapon.familyday.Adapter.SuccessAdapter;
import com.example.ptwitchapon.familyday.Model.SaveModel;

public class SuccessActivity extends AppCompatActivity {

    SuccessAdapter adapter;
    RecyclerView list;
    GridLayoutManager gridLayoutManager;
    ConnectionManager connect = new ConnectionManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        list = (RecyclerView) findViewById(R.id.list_success);
        adapter = new SuccessAdapter(getApplicationContext(), Utils.saveModel, new SuccessAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SaveModel profileBean, int position) {
                Intent intent = new Intent(SuccessActivity.this,MenuActivity.class);
                startActivity(intent);
                finish();
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
    }
}
