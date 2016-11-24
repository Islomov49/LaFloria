package com.iso.developer.lafloria.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.iso.developer.lafloria.FloriaActivity;
import com.iso.developer.lafloria.R;
import com.iso.developer.lafloria.syncbase.SignInGoogleMoneyHold;
import com.mxn.soul.flowingdrawer_core.MenuFragment;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.iso.developer.lafloria.FloriaActivity.lFragmentManager;
import static com.iso.developer.lafloria.FloriaActivity.mLeftDrawerLayout;

/**
 * Created by developer on 13.10.2016.
 */

public class MyMenu extends MenuFragment {
    private static final String TAG = "devdebug";
    LinearLayout adminMode,products,orders;
    SignInGoogleMoneyHold signInGoogleMoneyHold;
    static  CircleImageView circleImageView;
    SharedPreferences spref;
    Context context;
    static  TextView accountName,userEmail;
    static DownloadImageTask imagetask;
    boolean downloadnycCanRest = true;
    Uri imageUri;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container,
                false);
        context = getActivity();
        spref = context.getSharedPreferences("infoFirst", context.MODE_PRIVATE);

        circleImageView = (CircleImageView) view.findViewById(R.id.accountPhoto);
        adminMode = (LinearLayout) view.findViewById(R.id.adminMenu);
        products = (LinearLayout) view.findViewById(R.id.productList);
        orders = (LinearLayout) view.findViewById(R.id.myOrders);
        accountName = (TextView) view.findViewById(R.id.nameAccount);
        userEmail = (TextView) view.findViewById(R.id.emailAdress);
        imagetask = new DownloadImageTask(circleImageView);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseUser userim = FirebaseAuth.getInstance().getCurrentUser();
                if(userim !=null){
                    Log.d(TAG, "UREEE ");
                    return;

                }
                signInGoogleMoneyHold = new SignInGoogleMoneyHold(getActivity(), new SignInGoogleMoneyHold.UpdateSucsess() {
                    @Override
                    public void updateToSucsess() {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            accountName.setText(user.getDisplayName());
                            userEmail.setText(user.getEmail());
                            try {
                                if (user.getPhotoUrl() != null) {
                                    imagetask = new DownloadImageTask(circleImageView);
                                    imagetask.execute(user.getPhotoUrl().toString());
                                    imageUri = user.getPhotoUrl();
                                }
                            } catch (Exception o) {

                            }}


                        Log.d(TAG,"Update Sucesess");
                    }

                    @Override
                    public void updateToFailed() {
                        Log.d(TAG, "updateToFailed: ");
                    }
                });
                if (spref.getBoolean("FIRSTSYNC", true)) {
                    signInGoogleMoneyHold.openDialog();
                } else
                    signInGoogleMoneyHold.regitUser();

            }
        });
        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        lFragmentManager.displayMainWindow();

                    }
                };
                Handler handler = new Handler();
                handler.postDelayed(runnable,200);
                mLeftDrawerLayout.closeDrawer();


            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        lFragmentManager.displayFragment(new ListOfOrdersFragment());

                    }
                };
                Handler handler = new Handler();
                handler.postDelayed(runnable,200);
                mLeftDrawerLayout.closeDrawer();

            }
        });

        adminMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        lFragmentManager.displayFragment(new AddingProductFragment());

                    }
                };
                Handler handler = new Handler();
                handler.postDelayed(runnable,200);
                mLeftDrawerLayout.closeDrawer();


            }
        });


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            accountName.setText(user.getDisplayName());
            userEmail.setText(user.getEmail());
            try {
                if (user.getPhotoUrl() != null) {
                    imagetask = new DownloadImageTask(circleImageView);
                    imagetask.execute(user.getPhotoUrl().toString());
                    imageUri = user.getPhotoUrl();
                }
            } catch (Exception o) {

            }
        }


        return  setupReveal(view) ;
    }

    public void onActivResultForDrawerCalls(int requestCode, int resultCode, Intent data){
        if (requestCode == SignInGoogleMoneyHold.RC_SIGN_IN) {
           if(signInGoogleMoneyHold!=null)
            signInGoogleMoneyHold.regitRequstGet(data);
        }}
    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        CircleImageView bmImage;

        public DownloadImageTask(CircleImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            File file = new File(context.getFilesDir(), "userphoto.jpg");
            if (file.exists()) {
                bmImage.setImageURI(Uri.parse(file.getAbsolutePath()));
            }
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;

            for (; true; ) {
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    mIcon11 = BitmapFactory.decodeStream(in);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                if (isCancelled()) break;
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                downloadnycCanRest = false;
                bmImage.setImageBitmap(result);
                File file = new File(context.getFilesDir(), "userphoto.jpg");
                FileOutputStream out = null;

                try {
                    out = new FileOutputStream(file);
                    result.compress(Bitmap.CompressFormat.JPEG, 100, out);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void onStopSuniy(){
        try {

            if (imagetask != null)
                imagetask.cancel(true);
            if (imagetask != null) {
                imagetask.cancel(true);
                imagetask = null;
            }
        } catch (Exception o) {

        }
    }
}
