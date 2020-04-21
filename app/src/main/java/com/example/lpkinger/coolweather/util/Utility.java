package com.example.lpkinger.coolweather.util;

import android.text.TextUtils;

import com.example.lpkinger.coolweather.db.City;
import com.example.lpkinger.coolweather.db.County;
import com.example.lpkinger.coolweather.db.Province;
import com.example.lpkinger.coolweather.gson.Weather;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Utility {
    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvinces=new JSONArray(response);
                for (int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public  static boolean handlerCityRespon(String response,int provinceId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allCities= new JSONArray(response);
                for(int i=0;i<allCities.length();i++){
                    JSONObject cityObject=allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountyResponse(String response,int cityid){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray counties=new JSONArray(response);
                for (int i=0;i<counties.length();i++){
                    JSONObject countyObject=counties.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setCityId(cityid);
                    county.setWeatherId(countyObject.getInt("weather_id"));
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
    * 将返回的json数据解析成weather实体类
  * */
    public static Weather handlerWeatherResponse(String respon){
        try {
            JSONObject jsonObject=new JSONObject(respon);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");
            String weatherContent=jsonArray.get(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);

        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
}


