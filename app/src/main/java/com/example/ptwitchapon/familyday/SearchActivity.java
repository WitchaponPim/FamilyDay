package com.example.ptwitchapon.familyday;

import android.icu.util.UniversalTimeScale;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.ptwitchapon.familyday.API.ConnectionManager;
import com.example.ptwitchapon.familyday.Adapter.RepNitiAdapter;
import com.example.ptwitchapon.familyday.Adapter.SearchAdapter;

public class SearchActivity extends AppCompatActivity{

    ListView list ;
    SearchView search;
    SearchAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        list = (ListView) findViewById(R.id.list_search);
        search = (SearchView) findViewById(R.id.search);

        setadapter();

    }

    private void setadapter(){

        adapter = new SearchAdapter(getApplicationContext(), Utils.regisModel.getPROFILE());
        list.setAdapter(adapter);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



            }
        });
    }
}
