package com.example.latte_ec2.deltest.main.cart;

import android.graphics.Color;
import android.support.annotation.IntRange;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.hasee.festec.exaple.app.Latte;
import com.example.hasee.festec.exaple.net.RestClient;
import com.example.hasee.festec.exaple.net.calback.ISuccess;
import com.example.latte.recycler.MultipleFields;
import com.example.latte.recycler.MultipleItemEntity;
import com.example.latte.recycler.MultipleRecyclerAdaper;
import com.example.latte.recycler.MultipleViewHolder;
import com.example.latte_ec2.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 * Created by hasee on 2017-12-20.
 */

public class ShopCartAdapter extends MultipleRecyclerAdaper {

    private boolean mIsSeletedAll = false;
    private ICartItemListener mICartItemListener = null;
    private double mTotalPrice = 0;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        //初始化总价
        for (MultipleItemEntity entity : data) {
            final double price = entity.getField(ShopCartFields.PRICE);
            final int count = entity.getField(ShopCartFields.COUNT);
            final double total = price * count;
            mTotalPrice = mTotalPrice + total;
        }
        //添加购物车item布局
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    public void setSeletedAll(boolean isSeletedAll) {
        this.mIsSeletedAll = isSeletedAll;
    }

    public void setICartItemListener(ICartItemListener listener) {
        this.mICartItemListener = listener;
        mICartItemListener.onItemClick(mTotalPrice);
    }

    public double getTotalPrice(){
        return mTotalPrice;
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                //取出所有值
                final int id = entity.getField(MultipleFields.ID);
                final String thume = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCartFields.TITLE);
                final String desc = entity.getField(ShopCartFields.DESC);
                final int count = entity.getField(ShopCartFields.COUNT);
                final double price = entity.getField(ShopCartFields.PRICE);
                //取出所有控件
                final AppCompatImageView imageThume = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final IconTextView iconIsSeleted = holder.getView(R.id.icon_item_shop_cart);

                //在左侧钩钩渲染之前改变全态与否状态
                entity.setField(ShopCartFields.IS_SELETED, mIsSeletedAll);
                final boolean isSeleted = entity.getField(ShopCartFields.IS_SELETED);

                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvCount.setText(String.valueOf(count));
                tvPrice.setText(String.valueOf(price));
                Glide.with(mContext)
                        .load(thume)
                        .into(imageThume);

                //根据数据状态显示左侧的勾选
                if (isSeleted) {
                    iconIsSeleted.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                } else {
                    iconIsSeleted.setTextColor(Color.GRAY);
                }

                //添加点击事件
                iconIsSeleted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSeleted = entity.getField(ShopCartFields.IS_SELETED);
                        if (currentSeleted) {
                            iconIsSeleted.setTextColor(Color.GRAY);
                            entity.setField(ShopCartFields.IS_SELETED, false);
                        } else {
                            iconIsSeleted.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                            entity.setField(ShopCartFields.IS_SELETED, true);
                        }
                    }
                });

                //添加点击事件
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCartFields.COUNT);
                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            RestClient.builder()
                                    .url("")
                                    .params("count", currentCount)
                                    .success(new ISuccess() {
                                        @Override
                                        public void OnSuccess(String respones) {
                                            int countNum = Integer.parseInt(tvCount.getText().toString());
                                            countNum--;
                                            tvCount.setText(String.valueOf(countNum));
                                            if (mICartItemListener != null){
                                                mTotalPrice = mTotalPrice - price;
                                                final double itemToal = countNum * price;
                                                mICartItemListener.onItemClick(itemToal);
                                            }
                                        }
                                    })
                                    .build()
                                    .post();
                        }
                    }
                });

                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = entity.getField(ShopCartFields.COUNT);
                        RestClient.builder()
                                .url("")
                                .params("count", currentCount)
                                .success(new ISuccess() {
                                    @Override
                                    public void OnSuccess(String respones) {
                                        int countNum = Integer.parseInt(tvCount.getText().toString());
                                        countNum++;
                                        tvCount.setText(String.valueOf(countNum));
                                        if (mICartItemListener != null){
                                            mTotalPrice = mTotalPrice + price;
                                            final double itemToal = countNum * price;
                                            mICartItemListener.onItemClick(itemToal);
                                        }
                                    }
                                })
                                .build()
                                .post();
                    }

                });
                break;
            default:
                break;
        }
    }

    //计算总价
    private void calculateTotalPrice(){
        final List<MultipleItemEntity> data = getData();
        double tempPrice = 0;
        for (MultipleItemEntity entity : data) {
            final double price = entity.getField(ShopCartFields.PRICE);
            final int count = entity.getField(ShopCartFields.COUNT);
            final double total = price * count;
            tempPrice = tempPrice + total;
        }
        mTotalPrice = tempPrice;
    }

    @Override
    public void remove(@IntRange(from = 0L) int position) {
        super.remove(position);
        calculateTotalPrice();
    }
}
