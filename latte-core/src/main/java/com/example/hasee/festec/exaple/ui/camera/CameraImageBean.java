package com.example.hasee.festec.exaple.ui.camera;

import android.net.Uri;

/**
 * Created by hasee on 2018-01-15.
 * 存储一些封装值
 * 拍到的照片的最终路径会被存储在这里，需要可以在这里获取
 */

public final class CameraImageBean {

    private Uri mPath = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri path){
        this.mPath = path;
    }
}
