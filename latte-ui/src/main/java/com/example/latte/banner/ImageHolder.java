package com.example.latte.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by hasee on 2017-11-03.
 * bigkoo.convenientbanner.ConvenientBanner 规定ViewHolder必须由一个实现了CBViewHolderCreator<T>的类来生成
 */

public class ImageHolder implements Holder<String> {

    private AppCompatImageView mImageView = null;
    //设置图片加载策略
    private static final RequestOptions BANNER_OPTIONS =
            new RequestOptions()
            .centerCrop()
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.ALL);

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide.with(context)
                .load(data)
                .apply(BANNER_OPTIONS)
                .into(mImageView);
//        Glide.with(context)
//                .load(data)
//                .apply(BANNER_OPTIONS)
//                .into(mImageView);
    }
}
