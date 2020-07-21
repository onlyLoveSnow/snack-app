package com.shuyue.snack.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.shuyue.snack.MyApplication;
import com.shuyue.snack.R;
import com.shuyue.snack.model.Snack;
import com.shuyue.snack.utils.Tips;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detailImage)
    ImageView image;

    @BindView(R.id.detailName)
    TextView name;

    @BindView(R.id.detailPrice)
    TextView price;

    @BindView(R.id.detailContent)
    TextView detail;

    @BindView(R.id.detailAddCartBtn)
    Button addCart;

    @BindView(R.id.detailFavorite)
    ImageView favorite;

    public static void actionStart(Context context, Snack snack) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("snack", snack);
        context.startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        fullScreen(this);

        ButterKnife.bind(this);

        Snack snack = (Snack) getIntent().getSerializableExtra("snack");

        if (snack != null) {
            image.setImageResource(snack.getImage());
            name.setText(snack.getName());
            price.setText("￥" + snack.getPrice());
            detail.setText(snack.getDetail());

            addCart.setOnClickListener(v -> {
                if (!MyApplication.getCartSnacks().contains(snack)) {
                    // 添加到购物车
                    snack.setCount(1);
                    MyApplication.getCartSnacks().add(snack);
                    Tips.show("已添加" + snack.getName() + "到购物车");

                    // 关闭Activity
                    finish();
                } else {
                    Tips.show("已在购物车中，不能重复添加");
                }
            });
        }
    }

    @OnClick(R.id.detailBack)
    void clickBack() {
        finish();
    }

    @OnClick(R.id.detailFavorite)
    void clickFavorite() {
        favorite.setImageResource(R.drawable.ic_baseline_favorite_24dp);
    }

    /**
     * 通过设置全屏，设置状态栏透明
     */
    private void fullScreen(Activity activity) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = ((Window) window).getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            WindowManager.LayoutParams attributes = window.getAttributes();
            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            attributes.flags |= flagTranslucentStatus;
            window.setAttributes(attributes);
        }
    }
}