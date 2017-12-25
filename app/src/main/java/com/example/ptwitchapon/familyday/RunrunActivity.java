package com.example.ptwitchapon.familyday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ptwitchapon.familyday.Adapter.MenuAdapter;

public class RunrunActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runrun);
        String[] list = {"2.5 กม."
                , "5.0 กม."
                , "7.5 กม."};
        MenuAdapter adapter = new MenuAdapter(getApplicationContext(), list);
        ListView listView = (ListView) findViewById(R.id.listRunrun);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;
                String title = null;
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
    }
}
