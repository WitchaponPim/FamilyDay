package com.example.ptwitchapon.familyday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;


public class ConfirmActivity extends AppCompatActivity {
    String TAG = "Poon";
    TextView txtqr,txtname,txtlastname,txtunit,txtact,txtfol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        txtqr = (TextView) findViewById(R.id.txt_qr);
        txtact = (TextView) findViewById(R.id.txt_act_total);
        txtfol = (TextView)  findViewById(R.id.txt_follow_total);
        txtname = (TextView) findViewById(R.id.txt_name);
        txtlastname = (TextView) findViewById(R.id.txt_lastname);
        txtunit = (TextView) findViewById(R.id.txt_unit);

        txtqr.setText(Utils.regisModel.getPROFILE().get(0).getRG_SMS());
        txtname.setText(Utils.regisModel.getPROFILE().get(0).getRG_FNAME());
        txtlastname.setText(Utils.regisModel.getPROFILE().get(0).getRG_LNAME());
        txtact.setText(String.valueOf(Utils.regisModel.getACTIVITIES().size()));
        txtunit.setText(Utils.regisModel.getPROFILE().get(0).getRG_UNIT());
        txtfol.setText(String.valueOf(Utils.regisModel.getPARENT().size()));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ยืนยันการลงทะเบียน");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
    }
}
