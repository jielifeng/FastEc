package com.example.latte_ec2.deltest.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;

import com.example.hasee.festec.exaple.delegate.bottom.BottomItemDelegate;
import com.example.hasee.festec.exaple.net.RestClient;
import com.example.hasee.festec.exaple.net.calback.ISuccess;
import com.example.latte.recycler.MultipleItemEntity;
import com.example.latte_ec2.R;
import com.example.latte_ec2.R2;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hasee on 2017-12-18.
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess, ICartItemListener {

    private static ShopCartAdapter mAdapter = null;

    @BindView(R2.id.tv_shop_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSeletedAll = null;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubCompat = null;
    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mTotalPrice = null;

    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSeletedAll() {
        final int tag = (int) mIconSeletedAll.getTag();
        if (tag == 0) {
            mIconSeletedAll.setTextColor
                    (ContextCompat.getColor(getContext(), R.color.app_main));
            mIconSeletedAll.setTag(1);
            mAdapter.setSeletedAll(true);
            //更新RecyclerView的显示状态
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        } else {
            mIconSeletedAll.setTextColor(Color.GRAY);
            mIconSeletedAll.setTag(0);
            mAdapter.setSeletedAll(false);
            mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSeletedItem() {
        final ArrayList<MultipleItemEntity> data = (ArrayList<MultipleItemEntity>) mAdapter.getData();
        //选出要删除的数据
        final List<MultipleItemEntity> deletedEntity = new ArrayList<>();
        int newPosition = 0;
        for (MultipleItemEntity entity : data) {
            final boolean isSeleted = entity.getField(ShopCartFields.IS_SELETED);
            entity.setField(ShopCartFields.POSITION, newPosition);
            if (isSeleted) {
                deletedEntity.add(entity);
            }
            newPosition++;
        }
        //删除数据
        for (int i = deletedEntity.size() - 1; i >= 0; i--) {
            int removePosition = deletedEntity.get(i).getField(ShopCartFields.POSITION);
            mAdapter.remove(removePosition);
        }
        final double price = mAdapter.getTotalPrice();
        mTotalPrice.setText(String.valueOf(price));
        checkItemCount();
    }

    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
        mTotalPrice.setText("￥0");
        checkItemCount();
    }

    //判断购物车内有没有商品
    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        if (count == 0) {
            final View stubView = mStubCompat.inflate();
            final AppCompatTextView tvToBuy = (AppCompatTextView) stubView.findViewById(R.id.tv_stub_to_buy);
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBinderView(@Nullable Bundle savedInstanceSate, View rootView) {
        mIconSeletedAll.setTag(0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("shop_cart.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();

    }

    @Override
    public void OnSuccess(String respones) {
        final ArrayList<MultipleItemEntity> data =
                new ShopCartDataConverter()
                        .setJsonData(respones)
                        .convert();
        mAdapter = new ShopCartAdapter(data);
        mAdapter.setICartItemListener(this);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        checkItemCount();
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = mAdapter.getTotalPrice();
        final String y = "￥";
        mTotalPrice.setText(y.concat(String.valueOf(price)));
    }
}
