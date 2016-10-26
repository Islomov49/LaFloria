package com.iso.developer.lafloria.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by developer on 07.10.2016.
 */

public class BitmapUtils {
    public static int dpToPx(int dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
