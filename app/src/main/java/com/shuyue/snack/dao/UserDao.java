package com.shuyue.snack.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.shuyue.snack.model.User;
import com.shuyue.snack.utils.Utils;

public class UserDao {

    // 实例化SharedPreferences对象
    private static SharedPreferences data = Utils.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);

    // Gson对象
    private static Gson gson = new Gson();

    public static boolean isLogin() {
        return data.getBoolean("isLogin", false);
    }

    public static void isLogin(boolean bool) {
        SharedPreferences.Editor edit = data.edit();
        edit.putBoolean("isLogin", bool);
        edit.apply();
    }

    /**
     * 获取已登录用户对象
     */
    public static User getUser() {
        String userJson = data.getString("user", "");
        return gson.fromJson(userJson, User.class);
    }

    public static void setUser(User user) {
        String userJson = gson.toJson(user);
        SharedPreferences.Editor edit = data.edit();
        edit.putString("user", userJson);
        edit.apply();
    }

    /**
     * 清楚登录用户信息和登录状态
     */
    public static void removeUserAndLoginStatus() {
        SharedPreferences.Editor edit = data.edit();
        edit.remove("user");
        edit.remove("isLogin");
        edit.apply();
    }

    public static void removeAll() {
        SharedPreferences.Editor edit = data.edit();
        edit.clear();
        edit.apply();
    }
}
