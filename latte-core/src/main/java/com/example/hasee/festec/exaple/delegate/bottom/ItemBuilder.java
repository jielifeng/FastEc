package com.example.hasee.festec.exaple.delegate.bottom;

import java.util.LinkedHashMap;

/**
 * Created by hasee on 2017-10-30.
 * 存储底部按钮的数据对象和与底部按钮对应的fragment
 */

public final class ItemBuilder {

    private final LinkedHashMap<BottomBean,BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder(){
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomBean bean,BottomItemDelegate delegate){
        ITEMS.put(bean,delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomBean,BottomItemDelegate> items){
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomBean,BottomItemDelegate> build(){
        return ITEMS;
    }
}
