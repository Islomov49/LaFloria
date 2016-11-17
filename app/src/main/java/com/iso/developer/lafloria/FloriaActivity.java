package com.iso.developer.lafloria;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iso.developer.lafloria.fragments.FlowersFragment;
import com.iso.developer.lafloria.fragments.MainViewPagerFragment;
import com.iso.developer.lafloria.fragments.MyMenu;
import com.iso.developer.lafloria.utils.LFragmentManager;
import com.mxn.soul.flowingdrawer_core.FlowingView;
import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout;

public class FloriaActivity extends AppCompatActivity {
//    NavigationView navigationView;
//    DrawerLayout drawerLayout;

    static
    {
        System.loadLibrary("NativeImageProcessor");
    }


    static public LeftDrawerLayout mLeftDrawerLayout;
    static public LFragmentManager lFragmentManager;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference rootReference = firebaseDatabase.getReference();

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floria_modern);
        lFragmentManager =new LFragmentManager(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setSubtitleTextAppearance(this,R.style.CustomTextAppearance);
        toolbar.setTitleTextColor(Color.parseColor("#414141"));
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(R.id.continer, new MainViewPagerFragment())
                .commit();

        toolbar.setSubtitle(R.string.connection);
        rootReference.child(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = dataSnapshot.getValue(Boolean.class);
                if(connected){
                    toolbar.setSubtitle(R.string.online_mode);
                }
                else {
                    toolbar.setSubtitle(R.string.connection_error);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        navigationView = (NavigationView) findViewById(R.id.navigation_view);
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
//        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){
//
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//
//
//                super.onDrawerOpened(drawerView);
//            }
//        };
//
//        drawerLayout.setDrawerListener(actionBarDrawerToggle);
//
//        actionBarDrawerToggle.syncState();
//
//
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem item) {
//
//                drawerLayout.closeDrawers();
//                return false;
//            }
//        });

        mLeftDrawerLayout = (LeftDrawerLayout) findViewById(R.id.id_drawerlayout);
        FragmentManager fm = getSupportFragmentManager();
         myMenu = (MyMenu) fm.findFragmentById(R.id.id_container_menu);
        FlowingView mFlowingView = (FlowingView) findViewById(R.id.sv);
        if (myMenu == null) {
            fm.beginTransaction().add(R.id.id_container_menu, myMenu = new MyMenu()).commit();
        }

        mLeftDrawerLayout.setFluidView(mFlowingView);
        mLeftDrawerLayout.setMenuFragment(myMenu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeftDrawerLayout.toggle();
            }
        });
    }
    MyMenu myMenu;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(myMenu!=null)
            myMenu.onActivResultForDrawerCalls(requestCode,resultCode,data);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        myMenu.onStopSuniy();
    }
}
