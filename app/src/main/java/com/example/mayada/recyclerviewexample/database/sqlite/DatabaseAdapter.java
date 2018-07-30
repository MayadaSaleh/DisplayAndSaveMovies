package com.example.mayada.recyclerviewexample.database.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by Mayada on 7/30/2018.
 */

public class DatabaseAdapter {

    private DatabaseHelper databaseHelper;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;


    public DatabaseAdapter(Context c){
        this.context =c;
    }

    public DatabaseAdapter open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public void insert(String name, String url) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.name, name);
        contentValue.put(DatabaseHelper.url, url);
        sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }


    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper.name, DatabaseHelper.url };

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Fetching data order by movie name
        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, DatabaseHelper.name);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
