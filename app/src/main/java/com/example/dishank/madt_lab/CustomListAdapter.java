package com.example.dishank.madt_lab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dishank on 4/1/2016.
 */
public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.MyViewHolder> {

    private List<DataProvider> datalist;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tname, tmatch, tgoal;
        public MyViewHolder(View itemView) {
            super(itemView);
            tname = (TextView) itemView.findViewById(R.id.pname);
            tmatch = (TextView) itemView.findViewById(R.id.pmatch);
            tgoal = (TextView) itemView.findViewById(R.id.pgoal);
        }
    }

    public CustomListAdapter(List<DataProvider> datalist){
        this.datalist = datalist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataProvider data = datalist.get(position);
        holder.tname.setText(data.getName());
        holder.tmatch.setText(data.getMatch());
        holder.tgoal.setText(data.getGoal());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}
