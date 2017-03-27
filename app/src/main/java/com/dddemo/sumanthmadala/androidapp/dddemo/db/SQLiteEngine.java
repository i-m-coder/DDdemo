package com.dddemo.sumanthmadala.androidapp.dddemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sumanthmadala on 3/26/17.
 */

public class SQLiteEngine extends SQLiteOpenHelper {

    Context context;
    private static String DD_DB_NAME = "dddemo.db";
    private static int DB_VERSION = 1;

    public SQLiteEngine(Context context) {
        super(context, DD_DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
