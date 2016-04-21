package com.example.dishank.madt_lab;

import android.content.*;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Dishank on 4/11/2016.
 */
public class ContentListAdapter extends RecyclerView.Adapter<ContentListAdapter.MyViewHolder>{


    private List<DataProviderContent> contentdata;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView cname,cgoal;
        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.contentimage);
            cname = (TextView) itemView.findViewById(R.id.playername);
            cgoal = (TextView) itemView.findViewById(R.id.playergoal);
        }
    }

    public ContentListAdapter(List<DataProviderContent> datalist){
        this.contentdata = datalist;
    }


    @Override
    public ContentListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContentListAdapter.MyViewHolder holder, int position) {
        DataProviderContent data = contentdata.get(position);
        holder.imageView.setImageResource(data.getImage());
        holder.cname.setText(data.getName());
        holder.cgoal.setText(data.getGoal());
    }

    @Override
    public int getItemCount() {
        return contentdata.size();
    }
}
