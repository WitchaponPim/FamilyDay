package com.example.ptwitchapon.familyday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        WebView WebViw = (WebView) findViewById(R.id.webView);
        WebViw.getSettings().setJavaScriptEnabled(true);
        WebViw.loadUrl("http://familyday.lpn.co.th/familyday_dev/familyday/register_activities/register");

    }
}
