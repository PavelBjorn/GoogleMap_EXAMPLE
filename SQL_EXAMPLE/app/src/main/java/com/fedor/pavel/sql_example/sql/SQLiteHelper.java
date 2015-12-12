package com.fedor.pavel.sql_example.sql;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteHelper extends SQLiteOpenHelper {

    // Main Data
    public static final String DATA_BASE_NAME = "test.db";
    public static final int DATA_BASE_VERSION = 2;

    // Tables Buy
    public static final String TABLE_BUY = "buy";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COUNT = "count";

    // Commands
    private static final String CREATE_TABLE_BUY = "CREATE TABLE " + TABLE_BUY
            + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_COUNT + " REAL" + ");";

    private static final String DROP_TABLE_BUY = "DROP TABLE " + TABLE_BUY + ";";


    public SQLiteHelper(Context context) {

        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);

    }

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);

    }

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {

        super(context, name, factory, version, errorHandler);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_BUY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_TABLE_BUY);
        onCreate(db);

    }
}
