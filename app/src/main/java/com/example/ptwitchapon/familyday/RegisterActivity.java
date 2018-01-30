package com.example.ptwitchapon.familyday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        String url = "http://familyday.lpn.co.th/familyday_dev/familyday/foreground/regis_fmd_event_formN/"+Utils.userModel.getProfile().getUsername();
        WebView WebViw = (WebView) findViewById(R.id.webView);
        WebViw.getSettings().setJavaScriptEnabled(true);
        WebViw.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url);
                return false; // then it is not handled by default action
            }
        });
        WebViw.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        WebViw.loadUrl(url);
    }
}
