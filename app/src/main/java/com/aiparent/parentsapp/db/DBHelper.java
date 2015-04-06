package com.aiparent.parentsapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.aiparent.parentsapp.config.SystemConstant;


/**
 * Created by weilanzhuan on 2015/4/3.
 */
public class DBHelper extends SQLiteOpenHelper {
    private Context mContext;

    public DBHelper(Context context){
        super(context, SystemConstant.DB_NAME, null,SystemConstant.DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
