package com.example.ptwitchapon.familyday;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.ptwitchapon.familyday.API.ConnectionManager;
import com.example.ptwitchapon.familyday.API.ScanQrCallbackListener;
import com.example.ptwitchapon.familyday.Model.RegisModel;
import com.squareup.okhttp.ResponseBody;

import retrofit.Retrofit;

public class RegisterActivity extends AppCompatActivity{
    String TAG = "Poon";
    ProgressDialog progressDialog;
    ConnectionManager connect = new ConnectionManager();
    ScanQrCallbackListener scanQrCallbackListener;

    @JavascriptInterface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        scanQrCallbackListener = new ScanQrCallbackListener() {
            @Override
            public void onResponse(RegisModel regisModel, Retrofit retrofit) {
                Utils.regisModel = regisModel;
                Log.d(TAG, "WEB TITLE : "+Utils.loca);
                validate(Integer.valueOf(Utils.regisModel.getSTATUS_ID()));

            }

            @Override
            public void onFailure(Throwable t) {

            }

            @Override
            public void onBodyError(ResponseBody responseBody) {

            }

            @Override
            public void onBodyErrorIsNull() {

            }
        };

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
        public void jumpToSearch(String et_runno,String stxt,String activityname){
        Utils.act_id = et_runno;
        activityname = activityname.trim();
        Utils.loca = activityname;
        connect.scanqr(scanQrCallbackListener,stxt,Utils.userModel.getProfile().getUsername(),et_runno);
        }
    }
    public void validate(int status){
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
            default:
                Toast.makeText(getApplicationContext(), Utils.regisModel.getSTATUS(), Toast.LENGTH_LONG).show();
                break;
        }
    }
    public void GogoSearch(){
        Intent intent = new Intent(RegisterActivity.this,SearchActivity.class);
        startActivity(intent);
        finish();
    }

    public void GogoConfirm(){
        Intent intent = new Intent(RegisterActivity.this,ConfirmActivity.class);
        startActivity(intent);
        finish();
    }

}

