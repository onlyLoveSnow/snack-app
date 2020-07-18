package com.shuyue.snack.adaptor;

import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shuyue.snack.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SnackLeftAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SnackLeftAdapter(List<String> typeList) {
        super(R.layout.item_snack_left, typeList);
    }

    /**
     * 设置item数据
     */
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
        // 第一个item默认选中状态
        if (baseViewHolder.getLayoutPosition() == 0) {
            baseViewHolder.setBackgroundResource(R.id.snackLeftType, R.color.colorBgWhite);
        }
        baseViewHolder.setText(R.id.snackLeftType, s);
    }

}
