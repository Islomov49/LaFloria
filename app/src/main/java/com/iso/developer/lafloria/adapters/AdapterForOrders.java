package com.iso.developer.lafloria.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iso.developer.lafloria.R;
import com.iso.developer.lafloria.datamoduls.OrderDataEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 02.11.2016.
 */

public class AdapterForOrders extends RecyclerView.Adapter<AdapterForOrders.ViewHolder> {
    ArrayList<OrderDataEntity> orderDataEntities;
    Context context;
    public AdapterForOrders(ArrayList<OrderDataEntity> orderDataEntities, Context context){
        this.orderDataEntities=orderDataEntities;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);
        AdapterForOrders.ViewHolder vh = new AdapterForOrders.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterForOrders.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return /*orderDataEntities.size()*/ 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public ViewHolder(View v) {
            super(v);
        }
    }

}
