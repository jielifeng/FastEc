package com.example.latte.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by hasee on 2017-11-03.
 * BaseViewHolder中已经封装了一系列给View赋值的方法，需要给View赋值时只需要调用相关的set方法即可
 * BaseViewHolder已经有了存储View的数组，不需要子类创建相关的属性。
 * 使用set方法赋值时BaseViewHolder会将相关的View存储起来
 */

public class MultipleViewHolder extends BaseViewHolder {

    public MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view){
        return new MultipleViewHolder(view);
    }
}
