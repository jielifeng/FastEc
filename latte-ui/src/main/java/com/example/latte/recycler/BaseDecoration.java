package com.example.latte.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * Created by hasee on 2017-11-05.
 */

public class BaseDecoration extends DividerItemDecoration{

    private BaseDecoration(@ColorInt int color, int size){
        setDividerLookup(new DividerLookupImpl(color,size));
    }

    public static BaseDecoration create(@ColorInt int color, int size){
        return new BaseDecoration(color,size);
    }



    //下划线对象
    private class DividerLookupImpl implements DividerItemDecoration.DividerLookup {

        private final int COLOR;
        private final int SIZE;

        public DividerLookupImpl(int color, int size){
            this.COLOR = color;
            this.SIZE = size;
        }

        @Override
        public Divider getVerticalDivider(int position) {
            return new Divider.Builder()
                    .size(SIZE)
                    .color(COLOR)
                    .build();
        }

        @Override
        public Divider getHorizontalDivider(int position) {
            return new Divider.Builder()
                    .size(SIZE)
                    .color(COLOR)
                    .build();
        }
    }
}
