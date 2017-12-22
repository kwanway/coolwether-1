package com.example.xxzxxz.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xxzxxz on 2017/12/22 0022.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond")
    public More more;

    public class More{
        @SerializedName("txt")
        public String info;
    }
}
