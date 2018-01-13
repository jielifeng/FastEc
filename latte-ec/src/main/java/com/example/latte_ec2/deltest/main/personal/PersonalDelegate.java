package com.example.latte_ec2.deltest.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hasee.festec.exaple.delegate.bottom.BottomItemDelegate;
import com.example.latte_ec2.R;
import com.example.latte_ec2.R2;
import com.example.latte_ec2.deltest.main.personal.list.ListAdapter;
import com.example.latte_ec2.deltest.main.personal.list.ListBean;
import com.example.latte_ec2.deltest.main.personal.list.ListItemType;
import com.example.latte_ec2.deltest.main.personal.order.OrderListDelegate;
import com.example.latte_ec2.deltest.main.personal.profile.UserProfileDelegate;
import com.example.latte_ec2.deltest.main.personal.souhuo.ShouhuoDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hasee on 2017-12-26.
 */

public class PersonalDelegate extends BottomItemDelegate {

    public static final String ORDER_TYPE = "ORDER_TYPE";

    private Bundle mArgs = null;
    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceSate, View rootView) {

        ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setText("收货地址")
                .build();

        ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        //设置adapter
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    @OnClick(R2.id.tv_all_order)
    void onClickAllOrder(){
        mArgs.putString(ORDER_TYPE,"all");
        startOrderListByType();
    }

    private void startOrderListByType(){
        final OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }

    @OnClick(R2.id.ll_receive)
    void onClikAllReceive(){
        final ShouhuoDelegate delegate = new ShouhuoDelegate();
        getParentDelegate().getSupportDelegate().start(delegate);
    }

    @OnClick(R2.id.img_user_avatar)
    void onClickAvatar(){
        getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
    }
}
