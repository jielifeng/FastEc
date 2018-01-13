package com.example.latte_ec2.deltest.main;

import android.graphics.Color;

import com.example.hasee.festec.exaple.delegate.bottom.BaseBottomDelegate;
import com.example.hasee.festec.exaple.delegate.bottom.BottomBean;
import com.example.hasee.festec.exaple.delegate.bottom.BottomItemDelegate;
import com.example.hasee.festec.exaple.delegate.bottom.ItemBuilder;
import com.example.latte_ec2.deltest.main.cart.ShopCartDelegate;
import com.example.latte_ec2.deltest.main.discover.DiscoverDelegate;
import com.example.latte_ec2.deltest.main.index.IndexDeleagte;
import com.example.latte_ec2.deltest.main.personal.PersonalDelegate;
import com.example.latte_ec2.deltest.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by hasee on 2017-10-30.
 */

public class EcButtomDeleagte extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        LinkedHashMap<BottomBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomBean("{fa-home}","首页"),new IndexDeleagte());
        items.put(new BottomBean("{fa-sort}","分类"),new SortDelegate());
        items.put(new BottomBean("{fa-compass}","发现"),new DiscoverDelegate());
        items.put(new BottomBean("{fa-shopping-cart}","购物车"),new ShopCartDelegate());
        items.put(new BottomBean("{fa-user}","我的"),new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return Color.parseColor("#ffff8800");
    }
}
