package com.example.ptwitchapon.familyday.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ptwitchapon.familyday.Model.ReportAllModel;
import com.example.ptwitchapon.familyday.R;

import java.util.List;

/**
 * Created by ptwitchapon on 9/1/2561.
 */

public class ReportAdapter extends BaseAdapter {

    List<ReportAllModel> models;
    Context context;

    public ReportAdapter(Context context, List<ReportAllModel> reportAllModel){
        this.models = reportAllModel;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
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
            view = mInflater.inflate(R.layout.list_report, viewGroup, false);

        TextView event = (TextView)view.findViewById(R.id.event);
        TextView total = (TextView)view.findViewById(R.id.total);

        event.setText(models.get(i).getEVENT());
        total.setText(models.get(i).getTOTAL());


        return view;
    }
}
