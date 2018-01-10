package com.example.ptwitchapon.familyday.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.ptwitchapon.familyday.Model.RepNitiModel;
import com.example.ptwitchapon.familyday.Model.ReportAllModel;
import com.example.ptwitchapon.familyday.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ptwitchapon on 9/1/2561.
 */

public class RepNitiAdapter extends BaseAdapter implements Filterable{

    List<RepNitiModel> models;
    List<RepNitiModel> mStringFilterList;
    Context context;
    ValueFilter valueFilter;
    public RepNitiAdapter(Context context, List<RepNitiModel> Model){
        this.models = Model;
        this.mStringFilterList = Model;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int i) {
        return models.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.list_report_niti, viewGroup, false);

        TextView niti = (TextView)view.findViewById(R.id.niti);
        TextView ev1 = (TextView)view.findViewById(R.id.ev1);
        TextView ev2 = (TextView)view.findViewById(R.id.ev2);
        TextView ev3 = (TextView)view.findViewById(R.id.ev3);
        TextView ev4 = (TextView)view.findViewById(R.id.ev4);
        TextView total = (TextView)view.findViewById(R.id.total_rep_niti);



        niti.setText(models.get(i).getNT_TNAME());
        ev1.setText(models.get(i).getS_RUNNING());
        ev2.setText(models.get(i).getS_SPORT());
        ev3.setText(models.get(i).getS_EVENT());
        ev4.setText(models.get(i).getS_EVENT2());
        int totals = Integer.valueOf(models.get(i).getS_RUNNING())
                +Integer.valueOf(models.get(i).getS_SPORT())
                +Integer.valueOf(models.get(i).getS_EVENT())
                +Integer.valueOf(models.get(i).getS_EVENT2());
        total.setText(String.valueOf(totals));

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
                List<RepNitiModel> filterList = new ArrayList();
                for (int i = 0; i < mStringFilterList.size(); i++) {
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
