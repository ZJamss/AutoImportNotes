package com.zjamss.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Program: AutoImportNote
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-05-30 21:05
 **/
public class http {
    public static Request request = new Retrofit.Builder()
            .baseUrl("https://zjamss.top")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Request.class);
}
