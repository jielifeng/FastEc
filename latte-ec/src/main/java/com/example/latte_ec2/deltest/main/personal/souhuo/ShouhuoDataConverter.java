package com.example.latte_ec2.deltest.main.personal.souhuo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.latte.recycler.DataConverter;
import com.example.latte.recycler.MultipleFields;
import com.example.latte.recycler.MultipleItemEntity;
import com.example.latte_ec2.deltest.main.cart.ShopCartFields;

import java.util.ArrayList;

/**
 * Created by hasee on 2018-01-11.
 */

public class ShouhuoDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");

        final int size = dataArray.size();
        for (int i=0;i<size;i++){
            final JSONObject data = dataArray.getJSONObject(i);
            final String title = data.getString("title");
            final String desc = data.getString("desc");
            final String thumb = data.getString("thumb");
            final int id = data.getInteger("id");
            final double price = data.getDouble("price");
            final int count = data.getInteger("count");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ShouhuoListItemType.ITEM_CONFIG)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.IMAGE_URL,thumb)
                    .setField(ShopCartFields.TITLE,title)
                    .setField(ShopCartFields.DESC,desc)
                    .setField(ShopCartFields.COUNT, count)
                    .setField(ShopCartFields.PRICE,price)
                    .setField(ShopCartFields.IS_SELETED,false)
                    .setField(ShopCartFields.POSITION,i)
                    .build();

            dataList.add(entity);
        }
        return dataList;
    }
}
