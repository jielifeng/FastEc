package com.example.latte_ec2.deltest.main.personal.souhuo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hasee.festec.exaple.delegate.LatteDelegate;
import com.example.hasee.festec.exaple.net.RestClient;
import com.example.hasee.festec.exaple.net.calback.ISuccess;
import com.example.latte.recycler.MultipleFields;
import com.example.latte.recycler.MultipleItemEntity;
import com.example.latte_ec2.R;
import com.example.latte_ec2.R2;

import java.util.List;

import butterknife.BindView;

/**
 * Created by hasee on 2018-01-10.
 */

public class ShouhuoDelegate extends LatteDelegate {
    @BindView(R2.id.rv_shouhuo_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shuhuo;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceSate, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .loader(getContext())
                .url("shop_cart.php")
                .success(new ISuccess() {
                    @Override
                    public void OnSuccess(String respones) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        mRecyclerView.setLayoutManager(manager);
                        final List<MultipleItemEntity> data =
                                new ShouhuoDataConverter().setJsonData(respones).convert();
                        MultipleItemEntity entity = MultipleItemEntity.builder()
                                .setField(MultipleFields.ITEM_TYPE,ShouhuoListItemType.ITEM_HEAD)
                                .build();
                        data.add(0,entity);
                        final ShouhuoListAdapter adapter = new ShouhuoListAdapter(data);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
