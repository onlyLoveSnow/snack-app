package com.shuyue.snack.adaptor;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shuyue.snack.R;
import com.shuyue.snack.model.Snack;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<Snack, BaseViewHolder> {

    public HomeAdapter(List<Snack> snacks) {
        super(R.layout.item_home_snack, snacks);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Snack snack) {
        baseViewHolder.setImageResource(R.id.homeSnackImage, snack.getImage());
    }
}
