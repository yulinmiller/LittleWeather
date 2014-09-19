package com.littleweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lliyu on 9/19/2014.
 */
public class LittleWeatherDB {
    public static final String DB_NAME = "little_weather";
    public static final int VERSION = 1;
    private LittleWeatherDB littleWeatherDB;
    private SQLiteDatabase db;

    private LittleWeatherDB(Context context) {
        LWOpenHelper dbHelper = new LWOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }
}
