package com.mws3000.bestfriendspethotel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_TABLE = "dogstable";
    private static final String DATABASE_NAME = "dogsdatabase";
    public static final int DATABASE_VERSION = 1;
    public static final String DOG_NAME_COLUMN = "dog_name";
    public static final String DOG_GENDER_COLUMN = "dog_gender";
    public static final String DOG_BREED_COLUMN = "dog_breed";


    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
