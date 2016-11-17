package com.iso.developer.lafloria.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iso.developer.lafloria.R;
import com.iso.developer.lafloria.utils.FiltersCollectionByTojiev;

/**
 * Created by developer on 18.11.2016.
 */

public class PhotoFiltersListAdapter extends RecyclerView.Adapter<PhotoFiltersListAdapter.ViewHolder> {
    Bitmap currentBitmap;
    Context context;
    AddPhotoEffects addPhotoEffects;
    public interface AddPhotoEffects{
         void  effectSelected(int positionEffect);
    }
    public  PhotoFiltersListAdapter(Context context, Bitmap currentBitmap, AddPhotoEffects addPhotoEffects){
        this.currentBitmap=currentBitmap;
        this.context = context;
        this.addPhotoEffects =addPhotoEffects;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.filter_item, parent, false);
        PhotoFiltersListAdapter.ViewHolder vh = new PhotoFiltersListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Log.d("ImageSizeTest", "onBindViewHolder: "+position);
        switch (position){
            case 0:
                holder.imageView.setImageBitmap( FiltersCollectionByTojiev.getStarLitFilter().processFilter(currentBitmap.copy(currentBitmap.getConfig(), true)));
                break;
            case 1:
                holder.imageView.setImageBitmap(  FiltersCollectionByTojiev.getLimeStutterFilter().processFilter(currentBitmap.copy(currentBitmap.getConfig(), true)));
                break;
            case 2:
                holder.imageView.setImageBitmap( FiltersCollectionByTojiev.getNightWhisperFilter().processFilter(currentBitmap.copy(currentBitmap.getConfig(), true)));
                break;
            case 3:
                holder.imageView.setImageBitmap( FiltersCollectionByTojiev.getAweStruckVibeFilter().processFilter(currentBitmap.copy(currentBitmap.getConfig(), true)));
                break;
            case 4:
                holder.imageView.setImageBitmap( FiltersCollectionByTojiev.getBlueMessFilter().processFilter(currentBitmap.copy(currentBitmap.getConfig(), true)));
                break;
            default:
                holder.imageView.setImageBitmap( FiltersCollectionByTojiev.getStarLitFilter().processFilter(currentBitmap.copy(currentBitmap.getConfig(), true)));
                break;
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPhotoEffects.effectSelected(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView imageView;
        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.imageWithFilter);
        }
    }
}
