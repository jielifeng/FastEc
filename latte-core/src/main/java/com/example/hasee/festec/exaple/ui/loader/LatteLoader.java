package com.example.hasee.festec.exaple.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.hasee.festec.exaple.util.file.DimenUtil;
import com.example.latte.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by hasee on 2017-08-20.
 * 通过这个类来使用加载页面,即连接服务器时的那个圆圈
 * 
 */

public class LatteLoader {

    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;

    private static final ArrayList<AppCompatDialog> LOADER = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoading(Context context,Enum<LoaderStyle> type){
        showLoading(context,type.name());
    }

    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LOADER);
    }

    public static void showLoading(Context context, String type)
    {

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(context,type);
        dialog.setContentView(avLoadingIndicatorView);

        //获取屏幕宽高
        int devicewidth = DimenUtil.getScreenWidth();
        int deviceheight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null){
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = devicewidth / LOADER_SIZE_SCALE;
            lp.height = deviceheight / LOADER_SIZE_SCALE;
            lp.height = lp.height+deviceheight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADER.add(dialog);
        dialog.show();
    }



    public static void stopLoading(){
        for (AppCompatDialog dialog : LOADER){
            if (dialog != null){
                if (dialog.isShowing()){
                    //cancel会执行一些回调
                    dialog.cancel();
                    //dismiss只是让loader消失
                    //dialog.dismiss();
                }
            }
        }
    }
}
