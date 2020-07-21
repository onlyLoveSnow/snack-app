package com.shuyue.snack.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shuyue.snack.model.Order;
import com.shuyue.snack.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private static DatabaseHelper dbHelper = new DatabaseHelper(Utils.getContext(), 1);

    static {
        dbHelper.getWritableDatabase();
    }

    /**
     * 保存订单数据
     */
    public static void saveOrder(List<Order> orders) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (Order order : orders) {
            ContentValues values = new ContentValues();
            values.put("name", order.getName());
            values.put("image", order.getImage());
            values.put("money", order.getMoney());
            values.put("time", order.getTime());
            values.put("username", order.getUsername());

            db.insert("orders", null, values);
        }
    }

    /**
     * 通过用户名查询订单数据
     */
    public static List<Order> findAllByUsername(String username) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Order> orders = new ArrayList<>();

        // 查询指定用户名订单
        Cursor cursor = db.query("orders", null, "username=?", new String[]{username}, null, null, "time desc");
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int image = cursor.getInt(cursor.getColumnIndex("image"));
                double money = cursor.getDouble(cursor.getColumnIndex("money"));
                String time = cursor.getString(cursor.getColumnIndex("time"));

                Order order = new Order(name, image, money, time);
                orders.add(order);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return orders;
    }
}
