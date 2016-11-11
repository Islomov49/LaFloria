package com.iso.developer.lafloria.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.iso.developer.lafloria.R;
import com.iso.developer.lafloria.utils.CustomTabLayout;

import java.util.ArrayList;


public class MainViewPagerFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private static final String TAG ="devdebug" ;
    ViewPager viewPager;
    public static int pos = 0;
    private ArrayList<Fragment> list;
    private SvyazWithFragments svyazToChild;
    private CustomTabLayout tabLayout;
    public MainViewPagerFragment() {
        // Required empty public constructor
    }
    public interface SvyazWithFragments{
        void invisibileTabLayoutAnimaton();
        void visibileTabLayoutAnimaton();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        svyazToChild=new SvyazWithFragments() {
            @Override
            public void invisibileTabLayoutAnimaton() {
                inVisibleAnimationTab();
            }

            @Override
            public void visibileTabLayoutAnimaton() {
                visibleAnimationTab();
            }
        };
    }
    private void inVisibleAnimationTab(){
        if(tabLayout!=null){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                tabLayout.animate().scaleX(0.5f).scaleY(0.5f).alpha(0.2f).setInterpolator(new AccelerateInterpolator()).setDuration(100l);
            }

        }
    }
    private void visibleAnimationTab(){
        if(tabLayout!=null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                tabLayout.animate().scaleX(1f).scaleY(1f).alpha(1f).setInterpolator(new DecelerateInterpolator()).setDuration(100l);
            }
        }
    }

    FlowersFragment flowersFragment;
    FlowersFragment flowersFragment2;
    FlowersFragment flowersFragment3;
    int fieldForScrolingPosition=0;
    boolean oldPos= false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_view_pager, container, false);

        tabLayout = (CustomTabLayout) view.findViewById(R.id.sliding_tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        list = new ArrayList<>();
        flowersFragment=new FlowersFragment();
        flowersFragment2=new FlowersFragment();
        flowersFragment3=new FlowersFragment();
        flowersFragment.setSvyaz(svyazToChild);
        flowersFragment2.setSvyaz(svyazToChild);
        flowersFragment3.setSvyaz(svyazToChild);

        list.add(flowersFragment);
        list.add(flowersFragment2);
        list.add(flowersFragment3);
        PagerAdapter adapter = new PagerAdapter
                (getActivity().getSupportFragmentManager(), list);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(fieldForScrolingPosition!=(int)(position+positionOffset))
                    switch (position){
                        case 0:
                            if(flowersFragment.isItVisible())
                                visibleAnimationTab();
                            else inVisibleAnimationTab();
                            fieldForScrolingPosition = position;
                            break;
                        case 1:
                            if(flowersFragment2.isItVisible())
                                visibleAnimationTab();
                            else inVisibleAnimationTab();
                            fieldForScrolingPosition = position;
                            break;
                        case 2:
                            if(flowersFragment3.isItVisible())
                                visibleAnimationTab();
                            else inVisibleAnimationTab();
                            fieldForScrolingPosition = position;
                            break;
                    }
                Log.d("test", "onScrolled: "+position+" = "+positionOffset +" = "+positionOffsetPixels   );


//                if(positionOffset==0 || position == fieldForScrolingPosition){
//                    oldPos = true;
//                }
//                if(fieldForScrolingPosition<position+positionOffset&&oldPos) {
//                    switch (position+1) {
//                        case 0:
//                            if (flowersFragment.isItVisible())
//                                visibleAnimationTab();
//                            else inVisibleAnimationTab();
//
//                            break;
//                        case 1:
//                            if (flowersFragment2.isItVisible())
//                                visibleAnimationTab();
//                            else inVisibleAnimationTab();
//                            break;
//                        case 2:
//                            if (flowersFragment2.isItVisible())
//                                visibleAnimationTab();
//                            else inVisibleAnimationTab();
//                            break;
//                    }
//                    fieldForScrolingPosition = position+1;
//                    oldPos=false;
//                }
//                if(fieldForScrolingPosition >   position+positionOffset&&oldPos){
//                    switch (position){
//                        case 0:
//                            if(flowersFragment.isItVisible())
//                                visibleAnimationTab();
//                            else inVisibleAnimationTab();
//
//                            break;
//                        case 1:
//                            if(flowersFragment2.isItVisible())
//                                visibleAnimationTab();
//                            else inVisibleAnimationTab();
//                            break;
//                        case 2:
//                            if(flowersFragment2.isItVisible())
//                                visibleAnimationTab();
//                            else inVisibleAnimationTab();
//                            break;
//                    }
//                    fieldForScrolingPosition = position;
//                    oldPos=false;
//                }
//                Log.d("test", "onScrolled: "+position+" = "+positionOffset +" = "+positionOffsetPixels   );
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(pos);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: ");
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabUnselected: ");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabReselected: ");
            }
        });
        tabLayout.setupWithViewPager(viewPager);


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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public class PagerAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Fragment> list;

        public PagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    FlowersFragment flowersFragment = (FlowersFragment) list.get(position);
                    return flowersFragment;
                case 1:
                    FlowersFragment flowersFragment2 = (FlowersFragment) list.get(position);
                    return flowersFragment2;
                case 2:
                    FlowersFragment flowersFragment3 = (FlowersFragment) list.get(position);
                    return flowersFragment3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Flowers";
                case 1:
                    return "Romantic";
                case 2:
                    return "Chocolate";
                default:
                    return "";
            }
        }
    }
}
