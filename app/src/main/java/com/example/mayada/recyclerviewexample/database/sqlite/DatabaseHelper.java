package com.example.mayada.recyclerviewexample.database.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mayada on 7/30/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String name = "name";
    public static final String url = "url";
    static final String DB_NAME = "mydb.DB";
    static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "movie";
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + name + " TEXT, " + url + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
