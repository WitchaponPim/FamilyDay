package com.example.ptwitchapon.familyday;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptwitchapon.familyday.API.ConnectionManager;
import com.example.ptwitchapon.familyday.API.CountCallbackListener;
import com.example.ptwitchapon.familyday.API.ScanQrCallbackListener;
import com.example.ptwitchapon.familyday.Adapter.InputFilterMinMax;
import com.example.ptwitchapon.familyday.Model.RegisModel;
import com.squareup.okhttp.ResponseBody;

import org.w3c.dom.Text;

import retrofit.Retrofit;


public class ScanQR extends AppCompatActivity {
    public static final int REQUEST_QR_SCAN = 4;
    ProgressDialog progressDialog;
    ConnectionManager connect = new ConnectionManager();
    ScanQrCallbackListener scanQrCallbackListener;
    CountCallbackListener countCallbackListener;
    String TAG = "Poon",title,actID;
    TextView title_act;
    EditText edt,total;
    Button btn,btn_count;
    LinearLayout count;
    ImageView title_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        scanQrCallbackListener = new ScanQrCallbackListener() {
            @Override
            public void onResponse(RegisModel regisModel, Retrofit retrofit) {
                Utils.loca = title;
                Utils.act_id = actID;
                Utils.regisModel = regisModel;
                validate(Integer.valueOf(Utils.regisModel.getSTATUS_ID()));
                Log.d(TAG, "onResponse: "+Utils.regisModel.getSTATUS_ID() + " : "+Utils.regisModel.getSTATUS());
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

        countCallbackListener = new CountCallbackListener() {
            @Override
            public void onResponse(String result, Retrofit retrofit) {
                progressDialog.dismiss();
                total.setText("1");
                Utils.toast(getApplicationContext(),"เพิ่มจำนวนเแล้ว");
                Log.d(TAG, "onResponse: "+result.toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Utils.toast(getApplicationContext(),t.toString());
                Log.d(TAG, "onFailure: "+t);
            }

            @Override
            public void onBodyError(ResponseBody responseBody) {

            }

            @Override
            public void onBodyErrorIsNull() {

            }
        };
        title = getIntent().getStringExtra("title");
        actID = getIntent().getStringExtra("actID");
        if (title==null||actID==null){
            title= getResources().getStringArray(R.array.gameList)[0];
            actID= getResources().getStringArray(R.array.gameList_ID)[0];
        }

        ImageView img = (ImageView) findViewById(R.id.qrscan);
        edt = (EditText) findViewById(R.id.edtsms) ;
        total = (EditText) findViewById(R.id.total);
        btn = (Button) findViewById(R.id.btn_submit);
        btn_count = (Button)findViewById(R.id.countbtn);
        title_act = (TextView) findViewById(R.id.title_act);
        count = (LinearLayout) findViewById(R.id.countArea);
        title_icon = (ImageView) findViewById(R.id.title_icon);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        title_act.setText(title);
        toolbar.setTitle(title);
        checktitle(title);
        total.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "100")});

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
        if(title.equals("ธรรมะในสวน")||title.equals("ชมการแข่งขันกีฬา")||title.equals("ดนตรีในสวน")){
            count.setVisibility(LinearLayout.VISIBLE);
            btn_count.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressDialog = ProgressDialog.show(ScanQR.this,"Please wait", "Loading...",true,false);
                    connect.count(countCallbackListener,total.getText().toString(),Utils.userModel.getProfile().getUsername(),actID);
                }
            });
        }




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = edt.getText().toString();
                progressDialog = ProgressDialog.show(ScanQR.this,"Please wait", "Loading...",true,false);
                connect.scanqr(scanQrCallbackListener,search,Utils.userModel.getProfile().getUsername(),actID);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    startActivityForResult(Intent.createChooser(intent, "Scan with"), REQUEST_QR_SCAN);
                } catch(Exception e){
                    //TODO handle exception
                    Toast.makeText(getBaseContext(),"Please Install Barcode Scanner",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_QR_SCAN && resultCode == RESULT_OK) {
            progressDialog = ProgressDialog.show(ScanQR.this,"Please wait", "Loading...",true,false);
            String contents = intent.getStringExtra("SCAN_RESULT");
            Log.d("Poon", contents);
            connect.scanqr(scanQrCallbackListener,contents,Utils.userModel.getProfile().getUsername(),actID);
        }
    }

    public void GogoSearch(){
        Intent intent = new Intent(ScanQR.this,SearchActivity.class);
        startActivity(intent);
        finish();
    }

    public void GogoConfirm(){
        Intent intent = new Intent(ScanQR.this,ConfirmActivity.class);
        startActivity(intent);
        finish();
    }


    public void validate(int status){
        progressDialog.dismiss();
        switch (status) {
            case 1:
                if(Utils.regisModel.getPROFILE().size()>1){
                    GogoSearch();
                }else{
                    GogoConfirm();
                }
                break;
            case 3:
                if(Utils.regisModel.getPROFILE().size()>1){
                    GogoSearch();
                }else{
                    GogoConfirm();
                }
                break;
            default:Toast.makeText(getApplicationContext(), Utils.regisModel.getSTATUS(), Toast.LENGTH_LONG).show();
                break;
        }
    }
    public void checktitle(String title){
        switch (title){
            case"เดิน-วิ่ง 2.5 กม.":title_icon.setImageResource(R.drawable.run);
                break;
            case"เดิน-วิ่ง 5.0 กม.":title_icon.setImageResource(R.drawable.run);
                break;
            case"ธรรมะในสวน":title_icon.setImageResource(R.drawable.tumma);
                break;
            case"ดนตรีในสวน":title_icon.setImageResource(R.drawable.music);
                break;
            case"เกมส์-คอนเสิร์ต":title_icon.setImageResource(R.drawable.concert);
                break;
            case"ฟุตซอล":title_icon.setImageResource(R.drawable.ball);
                break;
            case"แชร์บอล":title_icon.setImageResource(R.drawable.chair);
                break;
            case"สตรีทบาส":title_icon.setImageResource(R.drawable.basketball);
                break;
            case"แบดมินตันชายเดี่ยว":title_icon.setImageResource(R.drawable.badminton);
                break;
            case"แบดมินตันหญิงเดี่ยว":title_icon.setImageResource(R.drawable.badminton);
                break;
            case"แบดมินตันคู่ผสม":title_icon.setImageResource(R.drawable.badminton);
                break;
            case"แบดมินตันมิตรภาพ":title_icon.setImageResource(R.drawable.badminton);
                break;
            case"ปิงปองชายเดี่ยว":title_icon.setImageResource(R.drawable.pingpong);
                break;
            case"ปิงปองหญิงเดี่ยว":title_icon.setImageResource(R.drawable.pingpong);
                break;
            case"ปิงปองคู่ผสม":title_icon.setImageResource(R.drawable.pingpong);
                break;
            case"หมากฮอส":title_icon.setImageResource(R.drawable.checkers);
                break;
            case"กองเชียร์ และ เชียร์ลีดเดอร์":title_icon.setImageResource(R.drawable.cheerleader);
                break;
            case"ชมการแข่งขันกีฬา":title_icon.setImageResource(R.drawable.seeeee);
                break;
            default:
                break;

        }
    }
}
