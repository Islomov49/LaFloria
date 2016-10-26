package com.iso.developer.lafloria;

import android.app.Application;
import android.graphics.Typeface;

/**
 * Created by developer on 14.10.2016.
 */

public class AplicationFloria extends Application {
    @Override
    public void onCreate() {
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "robotoRegular.ttf");
//        TypefaceUtil.replaceFont("SERIF", "ralewayMedium.ttf",21);
//        TypefaceUtil.setDefaultFont(this, "SERIF", "openSansRegular.ttf");
    }
}
