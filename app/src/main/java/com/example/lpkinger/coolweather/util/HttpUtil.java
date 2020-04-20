package com.example.lpkinger.coolweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Callback;

public class HttpUtil {
    public static void sendOkHttpReqest(String address, Callback callback ){
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
