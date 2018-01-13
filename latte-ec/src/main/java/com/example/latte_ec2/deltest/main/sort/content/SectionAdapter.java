package com.example.latte_ec2.deltest.main.sort.content;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.latte_ec2.R;

import java.util.List;

/**
 * Created by hasee on 2017-11-08.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean,BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    /**
     * 头部的数据匹配
     * */
    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
        helper.setText(R.id.header,item.header);
        helper.setVisible(R.id.more,item.getIsMore());
        helper.addOnClickListener(R.id.more);
    }

    /**
     * 具体商品的数据匹配
     * */
    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {
        final SectionContentItemEntity itemEntity = item.t;
        final String thumb = itemEntity.getGoodsThumb();
        final String name = itemEntity.getGoodsName();
        final int goodsId = itemEntity.getGoodsId();

        helper.setText(R.id.tv,name);
        final AppCompatImageView goodsImageView = helper.getView(R.id.iv);

        Glide.with(mContext)
                .load(thumb)
                .apply(OPTIONS)
                .into(goodsImageView);
    }
}
