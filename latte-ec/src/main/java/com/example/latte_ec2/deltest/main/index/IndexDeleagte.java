package com.example.latte_ec2.deltest.main.index;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hasee.festec.exaple.delegate.bottom.BottomItemDelegate;
import com.example.latte.recycler.BaseDecoration;
import com.example.latte.refresh.RefreshHandler;
import com.example.latte_ec2.R;
import com.example.latte_ec2.R2;
import com.example.latte_ec2.deltest.main.EcButtomDeleagte;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;

/**
 * Created by hasee on 2017-10-30.
 */

public class IndexDeleagte extends BottomItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.icon_index_message)
    IconTextView mIconMessage = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceSate, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());

    }

    private void initRefreshLayout() {
        //加载时显示的圆圈的颜色，开始显示的位置和结束时的位置
        mRefreshLayout.setColorSchemeColors(
                Color.BLUE,
                Color.RED,
                Color.YELLOW);

        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        //第二个参数的意义是一列可以被分成几份
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(),R.color.app_backgroud),5));
        final EcButtomDeleagte ecButtomDeleagte = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecButtomDeleagte));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index.php");
    }

    @Override
    public Object setLayout() {
        return R.layout.deleagte_indx;
    }


}
