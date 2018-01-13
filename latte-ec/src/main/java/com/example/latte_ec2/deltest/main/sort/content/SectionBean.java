package com.example.latte_ec2.deltest.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by hasee on 2017-11-08.
 * json文件中data数组中的数据对象
 * 这个对象可以包含一个SectionContentItemEntity对象，用以包装SectionContentItemEntity对象。
 * 因为SectionAdapter要匹配头部数据和具体商品数据，这就需要两个数据类型，但SectionAdapter只能接受一个数据泛型，故
 * 其中一个数据类型需要包装
 * */

public class SectionBean extends SectionEntity<SectionContentItemEntity> {

    private boolean mIsMore = false;
    private int mId = -1;

    public SectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionBean(SectionContentItemEntity sectionContentItemEntity) {
        super(sectionContentItemEntity);
    }

    public void setId(int Id) {
        this.mId = Id;
    }

    public int getId() {
        return this.mId;
    }

    public void setIsMore(boolean IsMore) {
        this.mIsMore = IsMore;
    }

    public boolean getIsMore(){
        return this.mIsMore;
    }


}
