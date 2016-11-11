package com.iso.developer.lafloria.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.iso.developer.lafloria.FloriaActivity;
import com.iso.developer.lafloria.R;

/**
 * Created by developer on 01.11.2016.
 */

public class LFragmentManager {
    FragmentManager fragmentManager;
    FloriaActivity activity;
    public LFragmentManager(FloriaActivity floriaActivity){
        fragmentManager = floriaActivity.getSupportFragmentManager();
        activity = floriaActivity;
    }

    public void displayFragment(Fragment fragment){
//        activity.findViewById(R.id.continer).setVisibility(View.GONE);
        if (fragmentManager.findFragmentById(R.id.continer) != null && fragment.getClass().getName().equals(fragmentManager.findFragmentById(R.id.continer).getClass().getName()))
            return;
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        }
        fragmentManager
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .add(R.id.continer, fragment)
                .commit();
    }
    public void displayMainWindow(){
//        activity.findViewById(R.id.continer).setVisibility(View.VISIBLE);
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        }

    }

}
