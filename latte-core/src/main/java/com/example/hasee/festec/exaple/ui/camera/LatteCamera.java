package com.example.hasee.festec.exaple.ui.camera;

import android.net.Uri;

import com.example.hasee.festec.exaple.delegate.PermissionCheckerDelegate;
import com.example.hasee.festec.exaple.util.file.FileUtil;

/**
 * Created by hasee on 2018-01-15.
 * 照相模块的入口类
 */

public class LatteCamera {

    public static Uri creareCropFile(){
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG","jpg")).getPath());

    }

    public static void start(PermissionCheckerDelegate delegate){
        new CameraHandler(delegate).beginCameraDialog();
    }
}
