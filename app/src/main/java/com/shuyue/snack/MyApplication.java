package com.shuyue.snack;

import android.app.Application;

import com.shuyue.snack.model.Snack;

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

    public static void setCartSnacks(List<Snack> cartSnacks) {
        MyApplication.cartSnacks = cartSnacks;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
