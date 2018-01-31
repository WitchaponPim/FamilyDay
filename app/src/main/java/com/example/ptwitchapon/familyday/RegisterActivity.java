package com.example.ptwitchapon.familyday;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class RegisterActivity extends AppCompatActivity{



    @JavascriptInterface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        String url = "http://familyday.lpn.co.th/familyday_dev/familyday/foreground/regis_fmd_event_formN/"+Utils.userModel.getProfile().getUsername();
        WebView WebViws = (WebView) findViewById(R.id.webView);
        WebViws.getSettings().setJavaScriptEnabled(true);
        WebViws.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                view.loadUrl(url);
                return false; // then it is not handled by default action
            }
        });
        WebViws.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

        });
        WebViws.addJavascriptInterface(new WebAppInterface(this), "Android");
        WebViws.loadUrl(url);
    }

    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void backtoMenu() {
            finish();
        }

        @JavascriptInterface
        public void jumpToSearch(String et_runno,String stxt,String username){

        }
    }

}

