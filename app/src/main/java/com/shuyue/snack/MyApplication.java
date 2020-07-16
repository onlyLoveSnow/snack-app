package com.shuyue.snack;

import android.app.Application;

import com.shuyue.snack.model.Snack;
import com.shuyue.snack.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    /**
     * Application类上下文
     */
    private static MyApplication appContext;

    /**
     * 购物车的小吃
     */
    private static List<Snack> cartSnacks;

    public static MyApplication getInstance() {
        return appContext;
    }

    public static List<Snack> getCartSnacks() {
        return cartSnacks;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        // 初始化购物车集合
        cartSnacks = new ArrayList<>();

        // 初始化工具类
        Utils.init(this);
    }
}
