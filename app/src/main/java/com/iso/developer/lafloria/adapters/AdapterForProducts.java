package com.iso.developer.lafloria.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iso.developer.lafloria.R;
import com.iso.developer.lafloria.datamoduls.OrderDataEntity;

import java.util.ArrayList;

/**
 * Created by developer on 04.11.2016.
 */

public class AdapterForProducts extends RecyclerView.Adapter<AdapterForProducts.ViewHolder> {
    Context context;

    public AdapterForProducts( Context context){
        this.context = context;
    }

    @Override
    public AdapterForProducts.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item, parent, false);
        AdapterForProducts.ViewHolder vh = new AdapterForProducts.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterForProducts.ViewHolder holder, int position) {

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