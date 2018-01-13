package com.example.latte_ec2.deltest.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.hasee.festec.exaple.delegate.bottom.BottomItemDelegate;
import com.example.latte_ec2.R;
import com.example.latte_ec2.deltest.main.sort.content.ContentDeleagte;
import com.example.latte_ec2.deltest.main.sort.list.VerticalListDelegate;

/**
 * Created by hasee on 2017-10-30.
 */

public class SortDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceSate, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container,listDelegate);
        //getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDeleagte.newInstance(1));
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDeleagte.newInstance(1));

    }
}
