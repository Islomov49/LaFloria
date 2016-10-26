package com.iso.developer.lafloria.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iso.developer.lafloria.R;
import com.iso.developer.lafloria.adapters.AdapterForFlowerCategory;
import com.iso.developer.lafloria.datamoduls.CardDataCategoryModule;
import com.iso.developer.lafloria.utils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;


public class FlowersFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<CardDataCategoryModule> datalist;
    private AdapterForFlowerCategory adapterForFlower;
    private int overalScroll=0;
    private int intervalforScrolAnimation = 0;
    private Context context;
    private boolean keyForAnim=true;
    private MainViewPagerFragment.SvyazWithFragments svyazToParent;
    public FlowersFragment() {
        // Required empty public constructor
    }

    public void setSvyaz(MainViewPagerFragment.SvyazWithFragments svyaz){
        svyazToParent=svyaz;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
        intervalforScrolAnimation = BitmapUtils.dpToPx(48-40,context);

    }
    public boolean isItVisible(){
        if(intervalforScrolAnimation < overalScroll ){
            return false;
        }
        return true;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flowers, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        datalist = new ArrayList<>();

        // --- Simulyatsiya--- //
        datalist.add(new CardDataCategoryModule("All actually"));
        datalist.add(new CardDataCategoryModule("101 rose"));
        datalist.add(new CardDataCategoryModule("White rose"));
        datalist.add(new CardDataCategoryModule("Card bucket"));
        datalist.add(new CardDataCategoryModule("Red lips"));
        datalist.add(new CardDataCategoryModule("Wedding bucket"));

        adapterForFlower = new AdapterForFlowerCategory(datalist,getActivity());
        GridLayoutManager mLayoutManager = new GridLayoutManager(context,2);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapterForFlower);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                overalScroll += dy;

                if(intervalforScrolAnimation < overalScroll && keyForAnim ){
                    svyazToParent.invisibileTabLayoutAnimaton();
                    keyForAnim=false;
                    Log.d("testr", "INVIS");
                }
                else if (intervalforScrolAnimation > overalScroll && !keyForAnim){
                    svyazToParent.visibileTabLayoutAnimaton();
                    keyForAnim=true;
                    Log.d("testr", "VIS");
                }


//                Log.d("testr", "onScrolled: "+dy +" = "+overalScroll);
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        return view;
    }





}
