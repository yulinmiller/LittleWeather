package com.littleweather.app.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.littleweather.app.R;
import com.littleweather.app.db.LittleWeatherDB;
import com.littleweather.app.model.City;
import com.littleweather.app.model.Country;
import com.littleweather.app.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 麟 on 2014/9/21.
 */
public class ChooseAreaActivity extends Activity {
    public static final int LEVEL_PROVINCE=0;
    public static final int LEVEL_CITY=1;
    public static final int LEVEL_COUNTRY=2;
    
    private ProgressDialog progressDialog;
    private TextView textView;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private LittleWeatherDB littleWeatherDB;
    private List<String> dataList = new ArrayList<String>();
    
    private List<Province> provinceList;
    private List<City> cityList;
    private List<Country> countryList;
    
    private Province selectedProvince;
    private City selectedCity;
    private Country selectedCountry;
    
    private int currentLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_area);
        
        listView = (ListView) findViewById(R.id.list_view);
        textView = (TextView) findViewById(R.id.title_text);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        littleWeatherDB = LittleWeatherDB.getInstance(this);
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(currentLevel == LEVEL_PROVINCE){
                    selectedProvince = provinceList.get(position);
                    queryCities();
                }
                else if(currentLevel == LEVEL_CITY){
                    selectedCity = cityList.get(position);
                    queryCountries();
                }
                else {
                    selectedCountry = countryList.get(position);
                }
            }
        });
        queryProvinces();
        
    }

    private void queryProvinces() {
    }

    private void queryCountries() {
        
    }

    private void queryCities() {
        
    }
}
