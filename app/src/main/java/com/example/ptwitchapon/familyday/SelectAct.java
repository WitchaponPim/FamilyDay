package com.example.ptwitchapon.familyday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class SelectAct extends AppCompatActivity implements View.OnClickListener {
    Button act1;
    Button act2;
    Button act3;
    Button act4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        act1 = (Button) findViewById(R.id.act1);
        act2 = (Button) findViewById(R.id.act2);
        act3 = (Button) findViewById(R.id.act3);
        act4 = (Button) findViewById(R.id.act4);

        act1.setOnClickListener(this);
        act2.setOnClickListener(this);
        act3.setOnClickListener(this);
        act4.setOnClickListener(this);
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


    }

    @Override
    public void onClick(View v) {
        String head = null;
        String lists[] = null;
        Intent intent = null;
        ArrayList<String> list = new ArrayList<>();
        switch (v.getId()) {
            case R.id.act1:
                head = act1.getText().toString();
                intent = new Intent(SelectAct.this,ScanQR.class);
                lists = getResources().getStringArray(R.array.otherList);
                break;
            case R.id.act2:
                head = act2.getText().toString();
                intent = new Intent(SelectAct.this,RunrunActivity.class);
                lists = getResources().getStringArray(R.array.runList);
                break;
            case R.id.act3:
                head = act3.getText().toString();
                intent = new Intent(SelectAct.this,RunrunActivity.class);
                lists = getResources().getStringArray(R.array.sportList);
                break;
            case R.id.act4:
                head = act4.getText().toString();
                intent = new Intent(SelectAct.this,ScanQR.class);
                lists = getResources().getStringArray(R.array.otherList);
                break;
            default:
                break;
        }
        for (int i =0;i<lists.length;i++){
            list.add(lists[i]);
        }
        intent.putExtra("title", head);
        intent.putExtra("list",list);
        startActivity(intent);
    }
}
