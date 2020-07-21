package com.shuyue.snack.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context, int version) {
        super(context, "OrderStore.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建数据库
        String createOrders = "create table orders (" +
                "id integer primary key autoincrement," +
                "name text," +
                "image integer," +
                "money real," +
                "time text," +
                "username text)";

        db.execSQL(createOrders);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
