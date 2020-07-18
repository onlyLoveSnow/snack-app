package com.shuyue.snack.adaptor;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shuyue.snack.R;
import com.shuyue.snack.model.Snack;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 下单页面购物车列表适配器
 */
public class PlaceOrderAdapter extends BaseQuickAdapter<Snack, BaseViewHolder> {

    public PlaceOrderAdapter(List<Snack> snacks) {
        super(R.layout.item_place_order, snacks);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Snack snack) {
        baseViewHolder.setImageResource(R.id.placeOrderImage, snack.getImage())
                .setText(R.id.placeOrderName, snack.getName())
                .setText(R.id.placeOrderPrice, "￥" + snack.getPrice())
                .setText(R.id.orderCountBtn, String.valueOf(snack.getCount()));
    }
}
