package com.example.latte_ec2.deltest.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.hasee.festec.exaple.delegate.LatteDelegate;
import com.example.latte.recycler.ItemType;
import com.example.latte.recycler.MultipleFields;
import com.example.latte.recycler.MultipleItemEntity;
import com.example.latte.recycler.MultipleRecyclerAdaper;
import com.example.latte.recycler.MultipleViewHolder;
import com.example.latte_ec2.R;
import com.example.latte_ec2.deltest.main.sort.SortDelegate;
import com.example.latte_ec2.deltest.main.sort.content.ContentDeleagte;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * Created by hasee on 2017-11-07.
 */

public class SortRecyclerAdapter extends MultipleRecyclerAdaper {

    private final SortDelegate DELEAGTE;
    private int mPrePosition = 0;

    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        this.DELEAGTE = delegate;
        //添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);

    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()){
            case ItemType.VERTICAL_MENU_LIST :
                final String text = entity.getField(MultipleFields.TEXT);
                final boolean isClick = entity.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = holder.getAdapterPosition();
                        if (mPrePosition != currentPosition){
                            //还原上一个
                            getData().get(mPrePosition).setField(MultipleFields.TAG,false);
                            notifyItemChanged(mPrePosition);

                            //更新选中的item
                            entity.setField(MultipleFields.TAG,true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;

                            final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                            showContent(contentId);
                        }

                    }
                });

                //修改样式
                if (!isClick){
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.we_char_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.item_backgroud));
                }else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext,R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }
                holder.setText(R.id.tv_vertical_item_name,text);
                break;
            default:
                break;
        }
    }

    private void showContent(int contentId){
        final ContentDeleagte deleagte =ContentDeleagte.newInstance(contentId);
        switchContent(deleagte);
    }

    private void switchContent(ContentDeleagte deleagte){
        final LatteDelegate contentDelagate =
                SupportHelper.findFragment(DELEAGTE.getChildFragmentManager(),ContentDeleagte.class);
        if (contentDelagate != null){
            contentDelagate.getSupportDelegate().replaceFragment(deleagte,false);
        }
    }
}
