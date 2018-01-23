package com.example.ptwitchapon.familyday;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptwitchapon.familyday.API.ConnectionManager;
import com.example.ptwitchapon.familyday.API.ScanQrCallbackListener;
import com.example.ptwitchapon.familyday.Model.RegisModel;
import com.squareup.okhttp.ResponseBody;

import org.w3c.dom.Text;

import retrofit.Retrofit;


public class ScanQR extends AppCompatActivity {
    public static final int REQUEST_QR_SCAN = 4;
    ProgressDialog progressDialog;
    ConnectionManager connect = new ConnectionManager();
    ScanQrCallbackListener scanQrCallbackListener;
    String TAG = "Poon",title,actID;
    TextView act_id;
    EditText edt;
    Button btn;
    LinearLayout count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        scanQrCallbackListener = new ScanQrCallbackListener() {
            @Override
            public void onResponse(RegisModel regisModel, Retrofit retrofit) {
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
        title = getIntent().getStringExtra("title");
        actID = getIntent().getStringExtra("actID");
        if (title==null||actID==null){
            title= getResources().getStringArray(R.array.gameList)[0];
            actID= getResources().getStringArray(R.array.gameList_ID)[0];
        }
        ImageView img = (ImageView) findViewById(R.id.qrscan);
        edt = (EditText) findViewById(R.id.edtsms) ;
        btn = (Button) findViewById(R.id.btn_submit);
        act_id = (TextView) findViewById(R.id.actID);
        count = (LinearLayout) findViewById(R.id.countArea);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        act_id.setText(actID);

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
        if(title.equals("ธรรมะในสวน")){
            count.setVisibility(LinearLayout.VISIBLE);
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
    }

    public void GogoConfirm(){
        Intent intent = new Intent(ScanQR.this,ConfirmActivity.class);
        startActivity(intent);
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
}
