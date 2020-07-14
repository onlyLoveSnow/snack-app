package com.shuyue.snack.adaptor;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shuyue.snack.R;
import com.shuyue.snack.model.Snack;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SnackRightAdapter extends BaseQuickAdapter<Snack, BaseViewHolder> {

    public SnackRightAdapter(List<Snack> snacks) {
        super(R.layout.item_snack_right, snacks);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Snack snack) {
        baseViewHolder.setImageResource(R.id.snackRightImage, snack.getImage());
        baseViewHolder.setText(R.id.snackRightName, snack.getName());
        baseViewHolder.setText(R.id.snackRightPrice, "￥" + String.valueOf(snack.getPrice()));
    }
}
