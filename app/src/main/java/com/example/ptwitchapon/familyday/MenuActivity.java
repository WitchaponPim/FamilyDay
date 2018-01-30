package com.example.ptwitchapon.familyday;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptwitchapon.familyday.Adapter.MenuAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TextView text = (TextView) findViewById(R.id.user);
        ListView listView = (ListView) findViewById(R.id.listView1);
        Button logout = (Button) findViewById(R.id.btn_logout);
        text.setText("User : " + Utils.userModel.getProfile().getFirst_name());
        mPrefs = getSharedPreferences("prefs_user", Context.MODE_PRIVATE);
        mEditor = mPrefs.edit();
        //เมนู
        final ArrayList<String> list = new ArrayList<>();
        list.add("ลงทะเบียนเข้าร่วมกิจกรรม[ScanQR]");
        list.add("ลงทะเบียนใหม่วันงาน");
        list.add("ตรวจสอบยอดลงทะเบียนแต่ละนิติ");
        list.add("ตรวจสอบยอดลงทะเบียนทั้งหมด");
        list.add("ตรวจสอบยอดลงทะเบียนกิจกรรมทั้งหมด");




        MenuAdapter adapter = new MenuAdapter(getApplicationContext(), list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;
                String title = null;
                switch (i) {
                    case 0:
                        intent = new Intent(MenuActivity.this, SelectAct.class);
                        title = list.get(0);
                        break;
                    case 1:
                        intent = new Intent(MenuActivity.this, RegisterActivity.class);
                        title = list.get(1);
                        break;
                    case 2:
                        intent = new Intent(MenuActivity.this, ReportByNitiActivity.class);
                        title = list.get(2);
                        break;
                    case 3:
                        intent = new Intent(MenuActivity.this, ReportNitiActivity.class);
                        title = list.get(3);
                    break;
                    case 4:
                        intent = new Intent(MenuActivity.this,ReportAllActivity.class);
                        title = list.get(4);
                        break;
                }
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
    public void showdialog(){
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ออกจากระบบ?");
        builder.setMessage("ต้องการออกจากระบบหรือไม่");
        builder.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mEditor.putString("user",null);
                mEditor.putString("password",null);
                mEditor.commit();
                Utils.userModel = null;
                Intent b = new Intent(MenuActivity.this,LoginActivity.class);
                startActivity(b);
                finish();
            }
        });
        builder.setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Utils.toast(getApplicationContext(),"");
            }
        });

        dialog = builder.create();//AlertDialog dialog; create like this outside onClick
        dialog.show();
    }
}
