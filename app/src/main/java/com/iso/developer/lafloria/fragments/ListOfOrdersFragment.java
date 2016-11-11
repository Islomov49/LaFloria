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
import com.iso.developer.lafloria.datamoduls.OrderDataEntity;
import com.iso.developer.lafloria.utils.LinearManagerWithOutEx;

import java.util.ArrayList;


public class ListOfOrdersFragment extends Fragment {
    RecyclerView rvOrdersHistory;
    AdapterForOrders adapterForOrders;
    ArrayList<OrderDataEntity> orderDataEntities;
    public ListOfOrdersFragment() {
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
        View view = inflater.inflate(R.layout.fragment_list_of_orders, container, false);
        rvOrdersHistory = (RecyclerView) view.findViewById(R.id.rvItemsOrders);
        orderDataEntities = new ArrayList<>();
        adapterForOrders  = new AdapterForOrders(orderDataEntities, getActivity());
        LinearManagerWithOutEx llm = new LinearManagerWithOutEx(getActivity());
        rvOrdersHistory.setLayoutManager(llm);

        rvOrdersHistory.setAdapter(adapterForOrders);
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
