package com.example.ptwitchapon.familyday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ptwitchapon.familyday.Adapter.MenuAdapter;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        String[] list = {"ลงทะเบียนหน้างาน"
                , "ลงทะเบียนล่วงหน้า"
                , "ตรวจสอบยอดลงทะเบียนแต่ละนิติ"
                , "ตรวจสอบยอดลงทะเบียนทั้งหมด"};

        MenuAdapter adapter = new MenuAdapter(getApplicationContext(), list);

        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;
                String title = null;
                switch (i) {
                    case 0:
                        intent = new Intent(MenuActivity.this, RegisterActivity.class);
                        title = "ลงทะเบียนหน้างาน";
                        break;
                    case 1:
                        intent = new Intent(MenuActivity.this, SelectAct.class);
                        title = "ลงทะเบียนล่วงหน้า";
                        break;
                    case 2:
                        intent = new Intent(MenuActivity.this, ScanQR.class);
                        title = "ตรวจสอบยอดลงทะเบียนแต่ละนิติ";
                        break;
                    case 3:
                        intent = new Intent(MenuActivity.this, ScanQR.class);
                        title = "ตรวจสอบยอดลงทะเบียนทั้งหมด";
                        break;
                }
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
    }
}
