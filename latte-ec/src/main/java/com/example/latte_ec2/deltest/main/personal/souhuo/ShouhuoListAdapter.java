package com.example.latte_ec2.deltest.main.personal.souhuo;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.latte.recycler.MultipleFields;
import com.example.latte.recycler.MultipleItemEntity;
import com.example.latte.recycler.MultipleRecyclerAdaper;
import com.example.latte.recycler.MultipleViewHolder;
import com.example.latte_ec2.R;
import com.example.latte_ec2.deltest.main.cart.ShopCartFields;

import java.util.List;

/**
 * Created by hasee on 2018-01-10.
 */

public class ShouhuoListAdapter extends MultipleRecyclerAdaper {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    protected ShouhuoListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ShouhuoListItemType.ITEM_HEAD, R.layout.item_shouhuo_list_head);
        addItemType(ShouhuoListItemType.ITEM_CONFIG,R.layout.item_shouhuo_list);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);

        switch (holder.getItemViewType()){
            case ShouhuoListItemType.ITEM_HEAD:
                final AppCompatImageView imageView = holder.getView(R.id.img_shouhuo_head);
                final AppCompatTextView shopName = holder.getView(R.id.tv_shouhuo_shopname);
                final AppCompatTextView status = holder.getView(R.id.tv_shouhuo_status);
                break;

            case ShouhuoListItemType.ITEM_CONFIG:
                final AppCompatImageView img = holder.getView(R.id.image_item_shouhuo);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shouhuo_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shouhuo_desc);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shouhuo_count);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shouhuo_price);

                final String title = entity.getField(ShopCartFields.TITLE);
                final String desc = entity.getField(ShopCartFields.DESC);
                final int count = entity.getField(ShopCartFields.COUNT);
                final double price = entity.getField(ShopCartFields.PRICE);
                final String imgUrl = entity.getField(MultipleFields.IMAGE_URL);

                Glide.with(mContext)
                        .load(imgUrl)
                        .apply(OPTIONS)
                        .into(img);
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvCount.setText("x"+String.valueOf(count));
                tvPrice.setText("￥"+ String.valueOf(price));
                break;
            default:
                break;
        }
    }
}
