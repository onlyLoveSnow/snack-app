package com.shuyue.snack.adaptor;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shuyue.snack.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SnackLeftAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SnackLeftAdapter(List<String> typeList) {
        super(R.layout.snack_left_item, typeList);
    }

    /**
     * 设置item数据
     */
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.snackLeftType, "今天爱淑悦" + (baseViewHolder.getAdapterPosition() - getHeaderLayoutCount() + "次"));
    }

}
