package com.example.hasee.festec.exaple.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by hasee on 2017-08-20.
 *
 */

public final class LoaderCreator {
    //存储Indicator，存储加载时显示的图形的样式
    private static final WeakHashMap<String,Indicator> LOADER_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(Context context, String type){

        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADER_MAP.get(type) == null)
        {
            final Indicator indicator = getIndicator(type);
            LOADER_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADER_MAP.get(type));
        return avLoadingIndicatorView;
    }

    //由于传进的type有多种类型，所以使用反射的方法创建Indicator
    private static Indicator getIndicator(String name){
        //isEmpty检查string是否已经分配内存
        if (name == null || name.isEmpty()){
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        //contains是检查是否包含指定字符串
        if (!name.contains(".")){
            final String defaultPackagName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackagName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
