package com.example.ptwitchapon.familyday.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ptwitchapon.familyday.R;

/**
 * Created by ptwitchapon on 15/12/2560.
 */

public class MenuAdapter extends BaseAdapter {
    Context context;
    String[] menu;

    public MenuAdapter(Context context,String[] menu) {
        this.context = context;
        this.menu = menu;

    }

    @Override
    public int getCount() {
        return menu.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.list_menu_row, viewGroup, false);

        TextView textView = (TextView)view.findViewById(R.id.textView1);
        textView.setText(menu[i]);



        return view;
    }
}
