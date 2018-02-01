package com.example.ptwitchapon.familyday.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ptwitchapon.familyday.Model.RegisModel;
import com.example.ptwitchapon.familyday.Model.RepNitiModel;
import com.example.ptwitchapon.familyday.R;
import com.example.ptwitchapon.familyday.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ptwitchapon on 18/1/2561.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MenuViewHolder> implements Filterable {
    Context context;
    List<RegisModel.PROFILEBean> models;
    List<RegisModel.PROFILEBean> mStringFilterList;
    ValueFilter valueFilter;
    SearchAdapter.OnItemClickListener listener;
    ProgressDialog progressDialog;
    public interface OnItemClickListener {
        void onItemClick(RegisModel.PROFILEBean profileBean, int position);

    }

    public SearchAdapter(Context context, List<RegisModel.PROFILEBean> models,SearchAdapter.OnItemClickListener listener) {
        Log.d("Poon", "SearchAdapter: "+String.valueOf(models.size()));
        this.context = context;
        this.models = models;
        this.listener = listener;
        mStringFilterList = models;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_search, parent, false);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        holder.setMenu(models, position);
    }

    @Override
    public int getItemCount() {
        return models.size();
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
            ArrayList<String> list = new ArrayList<>();


            if (constraint != null && constraint.length() > 0) {
                List<RegisModel.PROFILEBean> filterList = new ArrayList();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getRG_SMS().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                    if ((mStringFilterList.get(i).getRG_FNAME().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }

                    if ((mStringFilterList.get(i).getRG_MOBILE().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                    if ((mStringFilterList.get(i).getNT_TNAME().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                    if ((mStringFilterList.get(i).getRG_PARENT().toUpperCase()).contains(constraint.toString().toUpperCase())) {
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

    public class MenuViewHolder extends RecyclerView.ViewHolder{
        TextView sms, name, niti, tel,type;

        public MenuViewHolder(View itemView) {
            super(itemView);
            sms = (TextView) itemView.findViewById(R.id.sms);
            name = (TextView) itemView.findViewById(R.id.name);
            niti = (TextView) itemView.findViewById(R.id.niti);
            tel = (TextView) itemView.findViewById(R.id.tel);
            type = (TextView) itemView.findViewById(R.id.type);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(models.get(getAdapterPosition()),getAdapterPosition());
                }
            });
        }

        public void setMenu(final List<RegisModel.PROFILEBean> model, int position) {
            sms.setText(model.get(position).getRG_SMS());
            name.setText(model.get(position).getRG_FNAME());
            niti.setText(model.get(position).getNT_TNAME());
            tel.setText(model.get(position).getRG_MOBILE());
            type.setText(model.get(position).getRG_PARENT());

            if(model.get(position).getRG_PARENT().equals("เจ้าของ")) {
                type.setTextColor(context.getResources().getColor(R.color.colorHead));
            }else {
                type.setTextColor(context.getResources().getColor(R.color.colorFollow));
            }

        }

    }
}
