package com.example.coolweather.db;

import java.util.ArrayList;
import java.util.List;

import com.example.coolweather.model.City;
import com.example.coolweather.model.County;
import com.example.coolweather.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {
	public static final String DB_NAME="cool_weather";   //数据库名
	public static final int VERSION=1;    //数据库版本
	private static CoolWeatherDB coolWeatherDB;   //创建一个示例供类内使用
	private SQLiteDatabase db;
	//构造方法私有化
	private CoolWeatherDB(Context context)
	{
		CoolWeatherOpenHelper dbHelper=new CoolWeatherOpenHelper(context,DB_NAME,null,VERSION);
		db=dbHelper.getWritableDatabase();
	}
	
	 //获取CoolWeather实例，保证每次只能创建一个示例。
	public synchronized static CoolWeatherDB getInstance(Context context) 
	{                           
		if(coolWeatherDB==null)
			coolWeatherDB=new CoolWeatherDB(context);
		return coolWeatherDB;
	}
	
	public void saveProvince(Province province)    //将省份信息存储到数据库中
	{
		if(province!=null)
		{
			ContentValues values=new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);       //往数据库的Province表中插入一行数据
		}
	}
	
	public List<Province> loadProvinces()     //从数据库中读取所有省份的信息，读入到list中
	{
		List<Province> list=new ArrayList<Province>();
		Cursor cursor=db.query("Pronivnce", null, null, null, null, null, null);
		if(cursor.moveToFirst())
		{
			do
			{
				Province province=new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				list.add(province);			
			}while(cursor.moveToNext());
		}
		return list;
	}
	
	public void saveCity(City city)
	{
		if(city!=null)
		{
			ContentValues values=new ContentValues();
			values.put("city_name",city.getCityName());
			values.put("city_code",city.getCityName());
			values.put("province_ic",city.getProvinceId());
			db.insert("City", null, values);
		}
	}
	
	public List<City> loadCities(int provinceId)
	{
		List<City> list=new ArrayList<City>();
		Cursor cursor=db.query("City", null,"province_id=?", new String[] {String.valueOf(provinceId)}, null, null, null);
		if(cursor.moveToFirst())
		{
			City city=new City();
			city.setId(cursor.getInt(cursor.getColumnIndex("id")));
			city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
			city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
			city.setProvinceId(cursor.getInt(cursor.getColumnIndex("province_id")));
			list.add(city);
		}while(cursor.moveToNext());
		return list;
	}
	
	public void saveCounty(County county)
	{
		if(county!=null)
		{
			ContentValues values=new ContentValues();
			values.put("county_name",county.getCountyName());
			values.put("county_code",county.getCountyCode());
			values.put("city_id",county.getCityId());
			db.insert("County", null, values);
		}
	}
	
	public List<County> loadCounty(int cityId)
	{
		List<County> list=new ArrayList<County>();
		Cursor cursor=db.query("County",null, "city_id=?", new String[] {String.valueOf("cityId")}, null, null, null);
		if(cursor.moveToFirst())
		{
			do
			{
				County county=new County();
				county.setCityId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
				county.setCityId(cityId);
			}while(cursor.moveToNext());
		}
		return list;
	}
	
}
