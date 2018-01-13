package com.example.latte_ec2.deltest.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hasee.festec.exaple.delegate.LatteDelegate;
import com.example.hasee.festec.exaple.net.RestClient;
import com.example.hasee.festec.exaple.net.calback.ISuccess;
import com.example.latte.recycler.MultipleItemEntity;
import com.example.latte_ec2.R;
import com.example.latte_ec2.R2;
import com.example.latte_ec2.deltest.main.personal.PersonalDelegate;

import java.util.List;

import butterknife.BindView;

/**
 * Created by hasee on 2018-01-06.
 */

public class OrderListDelegate extends LatteDelegate {

    private String mType = null;

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString(PersonalDelegate.ORDER_TYPE);
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceSate, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url("order_list.php")
                .params("type",mType)
                .success(new ISuccess() {
                    @Override
                    public void OnSuccess(String respones) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new OrderListDataConverter().setJsonData(respones).convert();
                        final OrderListAdapter adapter = new OrderListAdapter(data);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
