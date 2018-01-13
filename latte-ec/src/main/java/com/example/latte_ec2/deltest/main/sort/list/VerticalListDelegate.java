package com.example.latte_ec2.deltest.main.sort.list;

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
import com.example.latte_ec2.deltest.main.sort.SortDelegate;

import java.util.List;

import butterknife.BindView;

/**
 * Created by hasee on 2017-11-06.
 * 左边的fragment
 */

public class VerticalListDelegate extends LatteDelegate {

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView(){
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceSate, View rootView) {
        initRecyclerView();
    }

    //在真正要显示的时候调用
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("sort_list.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void OnSuccess(String respones) {
                        final List<MultipleItemEntity> data =
                                new VerticalListDataConverter().setJsonData(respones).convert();
                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data,delegate);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();
    }
}
