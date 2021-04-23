package com.example.login_retrofit_api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static String Api_Base_Url="http://10.0.2.2:8080/testProject/webresources/";
    private static Retrofit retrofit;
    private static Gson gson;

    public static Retrofit getRetrofitInstance(){
        if (retrofit==null){
            gson=new GsonBuilder().setLenient().create();
            retrofit=new Retrofit.Builder().baseUrl(Api_Base_Url).addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
