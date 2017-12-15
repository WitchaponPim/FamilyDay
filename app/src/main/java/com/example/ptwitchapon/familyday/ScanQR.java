package com.example.ptwitchapon.familyday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ScanQR extends AppCompatActivity {
    public static final int REQUEST_QR_SCAN = 4;
    String TAG = "Poon";
    EditText edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        ImageView img = (ImageView) findViewById(R.id.qrscan);
        edt = (EditText) findViewById(R.id.edtsms) ;
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                startActivityForResult(Intent.createChooser(intent, "Scan with"), REQUEST_QR_SCAN);
            }
        });

    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_QR_SCAN && resultCode == RESULT_OK) {
            String contents = intent.getStringExtra("SCAN_RESULT");

            Log.d("Poon", contents);
            edt.setText(contents);
        }
    }
}
