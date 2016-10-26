package com.iso.developer.lafloria.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iso.developer.lafloria.R;
import com.iso.developer.lafloria.datamoduls.CardDataCategoryModule;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.iso.developer.lafloria.utils.BitmapUtils.*;

/**
 * Created by developer on 20.05.2016.
 */

public class AdapterForFlowerCategory extends RecyclerView.Adapter<AdapterForFlowerCategory.ViewHolder>{
        List<CardDataCategoryModule> mDataset;
    Context This;
        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextView;
            public ImageView mPhoto;
            public ViewHolder(View v) {
                super(v);
                mTextView=(TextView) v.findViewById(R.id.themee);
                mPhoto=(ImageView) v.findViewById(R.id.photo);

            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public AdapterForFlowerCategory(List<CardDataCategoryModule> myDataset, Context A1) {
            mDataset = myDataset;
            This=A1;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.carditems, parent, false);
            // set the view's size, margins, paddings and layout parameters

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            final CardDataCategoryModule item=mDataset.get(position);
            holder.mTextView.setText(item.getNameOfCategory());
                Picasso.with(This)
                        .load(R.drawable.temp).resize(dpToPx(200,This),dpToPx(200,This)).centerCrop()
                        .into(holder.mPhoto);
//
//            holder.mPhoto.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent aaaa=new Intent(This,Main2Activity.class);
//                    aaaa.putExtra("texttt",A12.getTexte());
//                    aaaa.putExtra("themmee",A12.getThemee());
//                    aaaa.putExtra("phhhooto",A12.getPhotoUri());
//                    aaaa.putExtra("uriiaudio",A12.getAudioUri());
//                    aaaa.putExtra("isfake",false);
//                    This.startActivity(aaaa);
//
//                }
//            });
//            Log.d("xxxx",A12.getPhotoUri());
        }

            // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

