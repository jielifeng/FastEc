package com.example.latte.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by hasee on 2017-11-03.
 */

public class HolderCreator implements CBViewHolderCreator<ImageHolder> {

    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
