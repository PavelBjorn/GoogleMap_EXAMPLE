package com.fedor.pavel.sql_example.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fedor.pavel.sql_example.model.BuyModel;

import java.sql.SQLException;
import java.util.ArrayList;


public class SQLiteManager {


    private Context context;
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase database;

    public SQLiteManager(Context context) {
        this.context = context;
        this.sqLiteHelper = new SQLiteHelper(this.context);
    }

    public void open() throws SQLException {

        this.database = sqLiteHelper.getWritableDatabase(); //открывает базу данных

    }

    public void close() {

        if (sqLiteHelper != null) {
            sqLiteHelper.close();
        }

    }

    public long insertBuy(BuyModel buyModel) {

        ContentValues cv = new ContentValues();

        cv.put(SQLiteHelper.COLUMN_NAME, buyModel.getName());
        cv.put(SQLiteHelper.COLUMN_COUNT, buyModel.getCount());

        return database.insert(SQLiteHelper.TABLE_BUY, null, cv);
    }

    public ArrayList<BuyModel> selectBuys() {

        Cursor cursor = database.query(SQLiteHelper.TABLE_BUY,
                new String[]{SQLiteHelper.COLUMN_ID, SQLiteHelper.COLUMN_NAME, SQLiteHelper.COLUMN_COUNT}
                ,null, null, null, null, null);

        ArrayList<BuyModel> models = new ArrayList<>();

        cursor.moveToFirst(); //Нужно точно знать тип

        while (!cursor.isAfterLast()) {
            long id = cursor.getLong(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_NAME));
            double count = cursor.getDouble(cursor.getColumnIndex(SQLiteHelper.COLUMN_COUNT));
            models.add(new BuyModel(id, name, count));
            cursor.moveToNext();
        }

        cursor.close();

        return models;
    }

    public int deleteBuy(long id){
        return database.delete(SQLiteHelper.TABLE_BUY,SQLiteHelper.COLUMN_ID+"="+id,null);
    }

}
