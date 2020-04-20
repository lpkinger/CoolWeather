package com.example.lpkinger.coolweather.gson;

import com.google.gson.annotations.SerializedName;

public class Now {
    @SerializedName("tmp")
    public String tmperature;
    @SerializedName("cond")
    public String weatherId;
    public More  more;

    public class More{
        @SerializedName("txt")
        public String info;
    }
}