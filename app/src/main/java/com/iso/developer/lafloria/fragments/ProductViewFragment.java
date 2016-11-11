package com.iso.developer.lafloria.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iso.developer.lafloria.R;
import com.iso.developer.lafloria.adapters.AdapterForOrders;
import com.iso.developer.lafloria.adapters.AdapterForProducts;
import com.iso.developer.lafloria.utils.LinearManagerWithOutEx;

public class ProductViewFragment extends Fragment {
    RecyclerView recyclerView;
    AdapterForProducts adapterForProducts;
    public ProductViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_view, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.listOfTovars);
        adapterForProducts  = new AdapterForProducts(getActivity());
        RecyclerView.LayoutManager layoutManager  = new LinearManagerWithOutEx(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterForProducts);
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
