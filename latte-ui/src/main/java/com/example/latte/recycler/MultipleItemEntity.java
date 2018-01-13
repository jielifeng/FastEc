package com.example.latte.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * Created by hasee on 2017-11-02.
 * 一个MultipleItemEntity对象就是一个JSONObject
 * 内部实现就是一个LinkedHashMap
 * 在这个对象中使用了一些关于内存管理的类
 * 如果RecyclerView想使用多布局，那么数据类必须继承MultiItemEntity
 */

public class MultipleItemEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUENE = new ReferenceQueue<>();
    private final LinkedHashMap<Object,Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object,Object>> FIELDS_REFERENCE =
            new SoftReference<>(MULTIPLE_FIELDS,ITEM_QUENE);

    public MultipleItemEntity(LinkedHashMap<Object,Object> fields){
        FIELDS_REFERENCE.get().putAll(fields);
    }

    public static MultipleItemEntityBuilder builder(){
        return new MultipleItemEntityBuilder();
    }


    @Override
    public int getItemType() {
        return (int)FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    public final <T> T getField(Object key){
        return (T)FIELDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<Object,Object> getFields(){
        return FIELDS_REFERENCE.get();
    }

    public final MultipleItemEntity setField(Object key,Object vaule){
        FIELDS_REFERENCE.get().put(key,vaule);
        return this;
    }


}
