package com.example.eeaproject.WebServices;

import com.google.gson.GsonBuilder;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BossClient {
    private static Retrofit getRetrofit(Cache cache)
    {
        HttpLoggingInterceptor theLogger = new HttpLoggingInterceptor();
        theLogger.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient theClient = new OkHttpClient.Builder().addInterceptor(theLogger).cache(cache).build();

        Retrofit retrofit = new Retrofit.Builder() //Takes a request and ensures the input is a JSON with the builder
                //.baseUrl("http://192.168.0.10:8080/ProductOrderingSystem-war/webresources/generic/")
                .baseUrl("http://192.168.0.10:8080/ProductOrderingSystem-war/webresources/generic/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setLenient()
                        .create()))
                .client(theClient)
                .build();

        return retrofit;
    }

    public static UserService getUserService(Cache cache)
    {
        UserService theService = getRetrofit(cache).create(UserService.class);
        return theService;
    }
}
