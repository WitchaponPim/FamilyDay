package com.example.ptwitchapon.familyday;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ptwitchapon.familyday.API.ConnectionManager;
import com.example.ptwitchapon.familyday.API.LoginCallbackListener;
import com.example.ptwitchapon.familyday.Model.UserModel;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.internal.Util;

import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    ConnectionManager connect = new ConnectionManager();
    LoginCallbackListener loginCallbackListener;
    int status;
    String TAG = "Poon";
    Button btn;
    EditText user,pass;

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = (Button) findViewById(R.id.btn_login);
        user = (EditText) findViewById(R.id.edtEmail);
        pass = (EditText) findViewById(R.id.edtPassword);

        mPrefs = getSharedPreferences("prefs_user", Context.MODE_PRIVATE);
        mEditor = mPrefs.edit();

        String a = mPrefs.getString("user",null);
        String b = mPrefs.getString("password",null);

        loginCallbackListener = new LoginCallbackListener() {
            @Override
            public void onResponse(UserModel userModel, Retrofit retrofit) {
                status = Integer.valueOf(userModel.getSuccess());
                Utils.userModel = userModel;
                Log.d(TAG, "onResponse: "+status);

                progressDialog.dismiss();

                if (status!=0){
                    onLoginSucced();
                }else {
                    onLoginFailed();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());
            }

            @Override
            public void onBodyError(ResponseBody responseBody) {
                Log.d(TAG, "onBodyError: "+responseBody.toString());
            }

            @Override
            public void onBodyErrorIsNull() {
                Log.d(TAG, "onBodyErrorIsNull: ");
            }


        };

        if(a!=null && b!=null){
            progressDialog = ProgressDialog.show(this, "Please wait", "Loading...", true, false);
            connect.login(loginCallbackListener,a,b);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    public void login() {
        Log.d(TAG, "Login");
        if (!validate()) {
            onLoginFailed();
            return;
        }
        progressDialog = ProgressDialog.show(this, "Please wait", "Loading...", true, false);

        String username = user.getText().toString();
        String password = pass.getText().toString();

            connect.login(loginCallbackListener, username, password);

    }
    private void onLoginSucced(){
        Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
        startActivity(intent);
    }
    private void onLoginFailed() {
        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
    }

    public boolean validate() {
        boolean valid = true;

        String username = user.getText().toString();
        String password = pass.getText().toString();
        mEditor.putString("user", username);
        mEditor.putString("password", password);
        mEditor.commit();
        if (username.isEmpty()) {
            user.setError("please fill username");
            valid = false;
        } else {
            user.setError(null);
        }

        if (password.isEmpty()) {
            pass.setError("please fill password");
            valid = false;
        } else {
            pass.setError(null);
        }

        return valid;
    }
}
