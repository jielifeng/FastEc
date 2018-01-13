package com.example.latte.banner;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.latte.R;

import java.util.ArrayList;

/**
 * Created by hasee on 2017-11-03.
 * 这个类负责为banner装配数据
 */

public class BannerCreator {
    public static void setDefault(ConvenientBanner convenientBanner,
                                  ArrayList<String> banbers,
                                  OnItemClickListener clickListener){

        convenientBanner
                .setPages(new HolderCreator(),banbers)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);


    }
}
