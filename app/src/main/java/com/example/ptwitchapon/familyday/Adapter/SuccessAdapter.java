package com.example.ptwitchapon.familyday.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.ptwitchapon.familyday.Model.RegisModel;
import com.example.ptwitchapon.familyday.Model.SaveModel;
import com.example.ptwitchapon.familyday.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ptwitchapon on 23/1/2561.
 */

public class SuccessAdapter extends RecyclerView.Adapter<SuccessAdapter.MenuViewHolder> {
    Context context;
    List<SaveModel> models;
    SuccessAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(SaveModel profileBean, int position);

    }

    public SuccessAdapter(Context context, List<SaveModel> models, OnItemClickListener listener) {
        this.context = context;
        this.models = models;
        this.listener = listener;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_save, parent, false);
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

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView sms, name, stat;

        public MenuViewHolder(View itemView) {
            super(itemView);
            sms = (TextView) itemView.findViewById(R.id.sms);
            name = (TextView) itemView.findViewById(R.id.Name);

            stat = (TextView) itemView.findViewById(R.id.status);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(models.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }

        public void setMenu(final List<SaveModel> model, int position) {
            sms.setText(model.get(position).getPROFILE().get(0).getRG_SMS());
            name.setText(model.get(position).getPROFILE().get(0).getRG_FNAME());

            stat.setText(model.get(position).getSTATUS());

            switch (Integer.valueOf(model.get(position).getSTATUS_ID())) {
                case 1:stat.setTextColor(context.getResources().getColor(R.color.colorButtonconfirm));
                    break;
                case 2:stat.setTextColor(context.getResources().getColor(R.color.colorButtoncancel));
                    break;
                default:
                    break;
            }

        }

    }
}
