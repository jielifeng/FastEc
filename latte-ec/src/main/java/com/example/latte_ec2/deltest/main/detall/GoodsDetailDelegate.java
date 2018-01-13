package com.example.latte_ec2.deltest.main.detall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.hasee.festec.exaple.delegate.LatteDelegate;
import com.example.latte_ec2.R;

/**
 * Created by hasee on 2017-11-06.
 */

public class GoodsDetailDelegate extends LatteDelegate {

    public static GoodsDetailDelegate create(){
        return new GoodsDetailDelegate();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceSate, View rootView) {

    }
}
