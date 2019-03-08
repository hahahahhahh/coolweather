package com.example.coolweather.model;

public class County {
	private int id;
	private String countyName;
	private String countyCode;
	private int cityId;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	
	public String getCountyName()
	{
		return countyName;
	}
	public void setCountyName(String cityName)
	{
		this.countyName=cityName;
	}
	
	public String getCountyCode()
	{
		return countyCode;
	}
	public void setCountyCode(String cityCode)
	{
		this.countyCode=cityCode;
	}
	
	public int getCityId()
	{
		return cityId;
	}
	public void setCityId(int provinceId)
	{
		this.cityId=provinceId;
	}
}
