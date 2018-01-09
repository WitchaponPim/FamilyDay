package com.example.ptwitchapon.familyday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ptwitchapon.familyday.Adapter.MenuAdapter;

import org.w3c.dom.Text;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TextView text = (TextView) findViewById(R.id.user);
        ListView listView = (ListView) findViewById(R.id.listView1);
        Button logout = (Button) findViewById(R.id.btn_logout);
        text.setText("User : "+Utils.userModel.getProfile().getFirst_name());

        //เมนู
        final String[] list = {"ลงทะเบียนเข้างาน [ScanQR]"
                , "ลงทะเบียนวันงาน"
                , "ตรวจสอบยอดลงทะเบียนแต่ละนิติ"
                , "ตรวจสอบยอดลงทะเบียนทั้งหมด"};

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
                        title = list[0];
                        break;
                    case 1:
                        intent = new Intent(MenuActivity.this, RegisterActivity.class);
                        title = list[1];
                        break;
                    case 2:
                        intent = new Intent(MenuActivity.this, ReportNitiActivity.class);
                        title = list[2];
                        break;
                    case 3:
                        intent = new Intent(MenuActivity.this, ReportNitiActivity.class);
                        title = list[3];
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
