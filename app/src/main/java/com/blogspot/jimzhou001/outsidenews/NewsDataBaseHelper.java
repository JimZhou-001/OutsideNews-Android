package com.blogspot.jimzhou001.outsidenews;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NewsDataBaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_NEWS = "create table News ("
            + "id integer primary key autoincrement,"
            + "title text,"
            + "imageId integer,"
            + "content text)";

    public static final String CREATE_READ_HISTORY = "create table ReadHistory ("
            + "id integer primary key autoincrement,"
            + "newsId integer)";

    public NewsDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_NEWS);
        sqLiteDatabase.execSQL(CREATE_READ_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exits News");
        sqLiteDatabase.execSQL("drop table if exits ReadHistory");
        onCreate(sqLiteDatabase);
    }

    public static void addNewsData(SQLiteDatabase sqLiteDatabase, String title, int imageId, String content) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("imageId", imageId);
        values.put("content", content);
        sqLiteDatabase.insert("News", null, values);
    }

    public static void addReadHistory(SQLiteDatabase sqLiteDatabase, int newsId) {
        ContentValues values = new ContentValues();
        values.put("newsId", newsId);
        sqLiteDatabase.insert("ReadHistory", null, values);
    }

}
