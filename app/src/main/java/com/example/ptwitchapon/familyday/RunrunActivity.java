package com.example.ptwitchapon.familyday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ptwitchapon.familyday.Adapter.MenuAdapter;
import com.google.gson.internal.bind.ArrayTypeAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class RunrunActivity extends AppCompatActivity {
        String TAG = "Poon";
        ArrayList<String> list;
        ArrayList<String> list_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runrun);
        list = getIntent().getStringArrayListExtra("list");
        list_id = getIntent().getStringArrayListExtra("list_id");

        MenuAdapter adapter = new MenuAdapter(getApplicationContext(), list);
        ListView listView = (ListView) findViewById(R.id.listRunrun);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra("title"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(RunrunActivity.this,ScanQR.class);
                String title = list.get(i);
                String actID = list_id.get(i);

                intent.putExtra("title", title);
                intent.putExtra("actID",actID);
                startActivity(intent);
//                finish();
            }
        });
    }
}
