package com.example.latte_ec2.deltest.main.sort.content;

/**
 * Created by hasee on 2017-11-08.
 * json文件中goods数组中的数据对象
 */

public class SectionContentItemEntity {
    private int mGoodsId = 0;
    private String mGoodsName = null;
    private String mGoodsThumb = null;

    public void setGoodsId(int goodsId) {
        this.mGoodsId = goodsId;
    }

    public int getGoodsId() {
        return mGoodsId;
    }

    public void setGoodsName(String goodsName) {
        this.mGoodsName = goodsName;
    }

    public String getGoodsName() {
        return mGoodsName;
    }

    public void setGoodsThumb(String goodsThumb) {
        this.mGoodsThumb = goodsThumb;
    }

    public String getGoodsThumb() {
        return mGoodsThumb;
    }
}
