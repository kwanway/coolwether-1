package com.example.xxzxxz.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xxzxxz on 2017/12/22 0022.
 */

public class Basic {
    //JSON中一些字段不太适合直接作为JAVA字段命名，使用SerializedName注解方式
    //让JSON字段和java字段建立映射联系
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
