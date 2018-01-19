package com.example.ptwitchapon.familyday.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.ptwitchapon.familyday.Model.RegisModel;
import com.example.ptwitchapon.familyday.Model.RepNitiModel;
import com.example.ptwitchapon.familyday.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ptwitchapon on 18/1/2561.
 */

public class SearchAdapter extends BaseAdapter implements Filterable {
    Context context;
    List<RegisModel.PROFILEBean> models;
    List<RegisModel.PROFILEBean> mStringFilterList;
    ValueFilter valueFilter;
    public SearchAdapter(Context context,  List<RegisModel.PROFILEBean> models){
        this.context = context;
        this.models = models;
        mStringFilterList = models;
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
            view = mInflater.inflate(R.layout.list_search, viewGroup, false);

        TextView sms = (TextView)view.findViewById(R.id.sms);
        TextView name = (TextView)view.findViewById(R.id.name);
        TextView niti = (TextView)view.findViewById(R.id.niti);
        TextView tel = (TextView)view.findViewById(R.id.tel);

        sms.setText(models.get(i).getRG_SMS());
        name.setText(models.get(i).getRG_FNAME()+" "+models.get(i).getRG_LNAME());
        niti.setText(models.get(i).getNT_TNAME());
        tel.setText(models.get(i).getRG_MOBILE());

        return view;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }
    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                List<RegisModel.PROFILEBean> filterList = new ArrayList();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getRG_SMS().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                    if ((mStringFilterList.get(i).getRG_FNAME().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                    if ((mStringFilterList.get(i).getRG_LNAME().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                    if ((mStringFilterList.get(i).getRG_MOBILE().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                    if ((mStringFilterList.get(i).getNT_TNAME().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;

            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            models = (List) results.values;
            notifyDataSetChanged();
        }
    }
}
