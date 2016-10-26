package com.iso.developer.lafloria.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iso.developer.lafloria.R;
import com.mxn.soul.flowingdrawer_core.MenuFragment;

/**
 * Created by developer on 13.10.2016.
 */

public class MyMenu extends MenuFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container,
                false);
        return  setupReveal(view) ;
    }
}
