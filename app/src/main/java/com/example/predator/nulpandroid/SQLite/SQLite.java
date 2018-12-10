package com.example.predator.nulpandroid.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Predator on 14.11.2018.
 */
public class SQLite extends SQLiteOpenHelper {

    public SQLite(Context c) {
        super(c, "Favourites", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table heroes( id INTEGER PRIMARY KEY AUTOINCREMENT, name text, " +
                "species text, created text, url_to_img text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists markers");
    }

    public boolean insert(String title, String description, String rate_average, String image_path) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", title);
        contentValues.put("species", description);
        contentValues.put("created", rate_average);
        contentValues.put("url_to_img", image_path);
        long res = db.insert("heroes", null, contentValues);
        System.out.print(res);
        return true;
    }

    public boolean delete(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = String.format("delete from heroes where name = '%s'", name);
        db.execSQL(sql);
        return true;
    }

    public Cursor getAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select  name,  species,created ,\n" +
                "url_to_img from heroes", null);
    }
}

