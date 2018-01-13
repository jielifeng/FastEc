package com.example.latte_ec2.deltest.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.example.latte.recycler.RgbValue;
import com.example.latte_ec2.R;

/**
 * Created by hasee on 2017-11-06.
 */

public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    //顶部距离
    private int mDistanceY = 0;
    //颜色变化速度
    private static final int SHOW_speed = 3;
    //定义变化颜色
    private final RgbValue RGB_VALUE = RgbValue.create(225, 124, 2);

    public TranslucentBehavior(Context context, AttributeSet arrts) {
        super(context, arrts);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        //增加滑动距离
        mDistanceY += dyConsumed;
        //toolbar的高度
        final int targeHeight = child.getBottom();

        //当滑动时，并且距离小于toolbar高度的时候，调整渐变色
        if (mDistanceY > 0 && mDistanceY <= targeHeight) {
            final float scale = (float) mDistanceY / targeHeight;
            final float alpha = scale * 255;
            child.setBackgroundColor(Color.argb((int) alpha, RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        } else if (mDistanceY > targeHeight) {
            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        }
    }
}
