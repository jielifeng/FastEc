package com.example.hasee.festec.exaple.util.file;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.hasee.festec.exaple.app.Latte;

/**
 * Created by hasee on 2017-08-22.
 */

public class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
