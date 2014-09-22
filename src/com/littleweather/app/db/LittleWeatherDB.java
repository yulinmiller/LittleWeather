package com.littleweather.app.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import com.littleweather.app.model.City;
import com.littleweather.app.model.Country;
import com.littleweather.app.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lliyu on 9/19/2014.
 */
public class LittleWeatherDB {
    public static final String DB_NAME = "little_weather";
    public static final int VERSION = 1;
    private static LittleWeatherDB littleWeatherDB;
    private SQLiteDatabase db;

    private LittleWeatherDB(Context context) {
        LittleWeatherOpenHelper dbHelper = new LittleWeatherOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }
    public synchronized static LittleWeatherDB getInstance(Context context){
        if(littleWeatherDB == null){
            littleWeatherDB =  new LittleWeatherDB(context);
        }
        return littleWeatherDB;
    }

    public void saveProvince(Province province){
        if(province!=null) {
            ContentValues values = new ContentValues();
            values.put(DBConstants.PROVINCE_NAME,province.getProvinceName());
            values.put(DBConstants.PROVINCE_CODE,province.getProvinceCode());
            db.insert(DBConstants.TABLE_PROVINCE,null,values);
        }
    }

    public List<Province> loadProvinces(){
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query(DBConstants.TABLE_PROVINCE,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex(DBConstants.PROVINCE_NAME)));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex(DBConstants.PROVINCE_CODE)));
                list.add(province);
            }
            while(cursor.moveToNext());
        }
        return list;
    }

    public void saveCity(City city){
        if(city != null){
            ContentValues values = new ContentValues();
            values.put(DBConstants.CITY_NAME,city.getCityName());
            values.put(DBConstants.CITY_CODE,city.getCityCode());
            values.put(DBConstants.PROVINCE_ID,city.getProvinceId());
            db.insert(DBConstants.TABLE_CITY,null,values);
        }
    }

    public List<City> loadCity(int provinceId){
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query(DBConstants.TABLE_CITY,null,DBConstants.PROVINCE_ID+" = ?",new String[]{String.valueOf(provinceId)},null,null,null);
        if(cursor.moveToFirst()){
            do{
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex(DBConstants.CITY_NAME)));
                city.setCityCode(cursor.getString(cursor.getColumnIndex(DBConstants.CITY_CODE)));
                city.setProvinceId(provinceId);
                list.add(city);
            }
            while(cursor.moveToNext());
        }
        return list;
    }

    public void saveCountry(Country country){
        if(country!= null){
            ContentValues values = new ContentValues();
            values.put(DBConstants.COUNTRY_NAME,country.getCountryName());
            values.put(DBConstants.COUNTRY_CODE,country.getCountryCode());
            values.put(DBConstants.CITY_ID,country.getCityId());
            db.insert(DBConstants.TABLE_COUNTRY,null,values);
        }
    }

    public List<Country> loadCountry(int cityId){
        List<Country> list = new ArrayList<Country>();
        Cursor cursor = db.query(DBConstants.TABLE_CITY,null,DBConstants.CITY_ID+" = ?",new String[]{String.valueOf(cityId)},null,null,null);
        if(cursor.moveToFirst()){
            do{
                Country country = new Country();
                country.setCountryName(cursor.getString(cursor.getColumnIndex(DBConstants.COUNTRY_NAME)));
                country.setCountryCode(cursor.getString(cursor.getColumnIndex(DBConstants.COUNTRY_CODE)));
                country.setCityId(cityId);
                list.add(country);
            }
            while(cursor.moveToNext());
        }
        return list;
    }

}
