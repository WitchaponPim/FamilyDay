package com.example.ptwitchapon.familyday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


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
        switch (v.getId()) {
            case R.id.act1:
                head = act1.getText().toString();
                break;
            case R.id.act2:
                head = act2.getText().toString();
                break;
            case R.id.act3:
                head = act3.getText().toString();
                break;
            case R.id.act4:
                head = act4.getText().toString();
                break;
            default:
                break;
        }
        Intent intent = new Intent(SelectAct.this,ScanQR.class);
        intent.putExtra("title", head);
        startActivity(intent);
    }
}
