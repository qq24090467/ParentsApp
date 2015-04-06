package com.aiparent.parentsapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by weilanzhuan on 2015/4/3.
 */
public class SQLiteUtils {
    private static volatile SQLiteUtils instance;

    private DBHelper                    dbHelper;
    private SQLiteDatabase              wDb;
    private SQLiteDatabase              rDb;

    private SQLiteUtils(Context context) {
        dbHelper = new DBHelper(context);
        wDb = dbHelper.getWritableDatabase();
        rDb = dbHelper.getReadableDatabase();
    }

    public static SQLiteUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (SQLiteUtils.class) {
                if (instance == null) {
                    instance = new SQLiteUtils(context);
                }
            }
        }
        return instance;
    }

    public SQLiteDatabase getWDb() {
        return wDb;
    }

    public SQLiteDatabase getRDb() {
        return rDb;
    }
}
