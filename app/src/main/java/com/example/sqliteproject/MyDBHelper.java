package com.example.sqliteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GroceriesDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_GROCERIES = "groceries";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCTITLE = "desc_title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_VALIDFROM = "validFrom";
    private static final String KEY_VALIDTILL = "validTill";

    public MyDBHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_GROCERIES+ "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT,"
                + KEY_DESCTITLE + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_VALIDFROM + " TEXT,"
                + KEY_VALIDTILL + " TEXT" +")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_GROCERIES);
        onCreate(db);
    }

    public void addGroceries(Groceries groceries)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,groceries.getTitle());
        values.put(KEY_DESCTITLE,groceries.getDescTitle());
        values.put(KEY_DESCRIPTION,groceries.getDescription());
        values.put(KEY_VALIDFROM,groceries.getValidFrom());
        values.put(KEY_VALIDTILL,groceries.getValidTill());
        database.insert(TABLE_GROCERIES,null,values);
    }

    public ArrayList<Groceries> getList()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_GROCERIES,null);
        ArrayList<Groceries> list = new ArrayList<>();

        while (cursor.moveToNext())
        {
            Groceries groceries = new Groceries();
            groceries.setId(cursor.getInt(0));
            groceries.setTitle(cursor.getString(1));
            groceries.setDescTitle(cursor.getString(2));
            groceries.setDescription(cursor.getString(3));
            groceries.setValidFrom(cursor.getString(4));
            groceries.setValidTill(cursor.getString(5));

            list.add(groceries);
        }
        return list;
    }


    public void deleteGroceries(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GROCERIES,KEY_ID +" = "+id,null);
    }
}
