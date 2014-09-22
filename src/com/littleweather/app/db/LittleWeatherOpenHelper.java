package com.littleweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lliyu on 9/19/2014.
 */
public class LittleWeatherOpenHelper extends SQLiteOpenHelper{

    public static final String CREATE_PROVINCE = "create table "+DBConstants.TABLE_PROVINCE+" (" +
            "id integer primary key autoincrement, " +
            DBConstants.PROVINCE_NAME +
            " text, " +
            DBConstants.PROVINCE_CODE +
            " text)";

    public static final String CREATE_CITY = "create table "+DBConstants.TABLE_CITY+" (" +
            "id integer primary key autoincrement, " +
            DBConstants.CITY_NAME +
            " text, " +
            DBConstants.CITY_CODE +
            " text, " +
            DBConstants.PROVINCE_ID +
            " integer)";

    public static final String CREATE_COUNTRY = "create table "+DBConstants.TABLE_COUNTRY+" (" +
            "id integer primary key autoincrement, " +
            DBConstants.COUNTRY_NAME +
            " text, " +
            DBConstants.COUNTRY_CODE +
            " text, " +
            DBConstants.CITY_ID +
            " integer)";

    public LittleWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
