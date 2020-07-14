package com.shuyue.snack.adaptor;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shuyue.snack.R;
import com.shuyue.snack.model.Snack;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<Snack, BaseViewHolder> {

    public HomeAdapter(List<Snack> snacks) {
        super(R.layout.item_home_snack, snacks);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Snack snack) {
//        baseViewHolder.setImageResource(R.id.homeSnackImage, snack.getImage());
        Picasso.get()
                .load("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1594707556&di=ba6606d9acaf3c68c006679ae7326674&src=http://n.sinaimg.cn/sinacn05/290/w680h410/20180713/ea14-hfhfwmu3172266.jpg")
                .into((ImageView) baseViewHolder.getView(R.id.homeSnackImage));
    }
}
