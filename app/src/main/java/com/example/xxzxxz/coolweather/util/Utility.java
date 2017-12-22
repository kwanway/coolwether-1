package com.example.xxzxxz.coolweather.util;

import android.text.TextUtils;

import com.example.xxzxxz.coolweather.db.City;
import com.example.xxzxxz.coolweather.db.County;
import com.example.xxzxxz.coolweather.db.Province;
import com.example.xxzxxz.coolweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xxzxxz on 2017/12/22 0022.
 */

public class Utility {
    //解析处理服务器返回的省级数据
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allPronvince = new JSONArray(response);
                for(int i=0;i<allPronvince.length();i++){
                    JSONObject provinceObeject=allPronvince.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObeject.getString("name"));
                    province.setProvinceCode(provinceObeject.getInt("id"));
                    province.save();
                }
                return true;
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    //解析市级数据
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCities = new JSONArray(response);
                for(int i=0;i<allCities.length();i++){
                    JSONObject cityObeject=allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObeject.getString("name"));
                    city.setCityCode(cityObeject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    //解析县级数据
    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCounties = new JSONArray(response);
                for(int i=0;i<allCounties.length();i++){
                    JSONObject countyObeject=allCounties.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObeject.getString("name"));
                    county.setWeatherId(countyObeject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    //将返回的JSON数据解析成Weather实体类
    public static Weather handleWeatherResponse(String response){
        try{
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");
            String weatherContent=jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
