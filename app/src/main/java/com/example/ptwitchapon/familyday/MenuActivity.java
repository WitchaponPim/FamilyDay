package com.example.ptwitchapon.familyday;

import android.content.Intent;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TextView text = (TextView) findViewById(R.id.user);
        ListView listView = (ListView) findViewById(R.id.listView1);
        Button logout = (Button) findViewById(R.id.btn_logout);
        text.setText("User : " + Utils.userModel.getProfile().getFirst_name());

        //เมนู
        final ArrayList<String> list = new ArrayList<>();
        list.add("ลงทะเบียนเข้างาน [ScanQR]");
        list.add("ลงทะเบียนวันงาน");
        list.add("ตรวจสอบยอดลงทะเบียนแต่ละนิติ");
        list.add("ตรวจสอบยอดลงทะเบียนทั้งหมด");
        list.add("ตรวจสอบยอดบวทะเยียนกิจกรรมทั้งหมด");




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
                Utils.userModel = null;
                onBackPressed();
            }
        });
    }
}
