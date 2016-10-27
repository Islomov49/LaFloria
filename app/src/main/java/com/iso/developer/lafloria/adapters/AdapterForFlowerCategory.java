package com.iso.developer.lafloria.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iso.developer.lafloria.FloriaActivity;
import com.iso.developer.lafloria.R;
import com.iso.developer.lafloria.datamoduls.CardDataCategoryModule;
import com.iso.developer.lafloria.fragments.AddingProductFragment;
import com.iso.developer.lafloria.fragments.MainViewPagerFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.iso.developer.lafloria.utils.BitmapUtils.*;

/**
 * Created by developer on 20.05.2016.
 */

public class AdapterForFlowerCategory extends RecyclerView.Adapter<AdapterForFlowerCategory.ViewHolder>{
        List<CardDataCategoryModule> mDataset;
    Context context;
        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextView;
            public ImageView mPhoto;
            public View mainView;
            public ViewHolder(View v) {
                super(v);
                mTextView=(TextView) v.findViewById(R.id.themee);
                mPhoto=(ImageView) v.findViewById(R.id.photo);
                mainView=v.findViewById(R.id.mainCardView);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public AdapterForFlowerCategory(List<CardDataCategoryModule> myDataset, Context A1) {
            mDataset = myDataset;

            context=A1;
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
                Picasso.with(context)
                        .load(R.drawable.temp).resize(dpToPx(200,context),dpToPx(200,context)).centerCrop()
                        .into(holder.mPhoto);
            holder.mainView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = ((FloriaActivity)context).getSupportFragmentManager();
                    fragmentManager
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(null)
                            .add(R.id.continer, new AddingProductFragment())
                            .commit();
                }
            });
        }

            // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

