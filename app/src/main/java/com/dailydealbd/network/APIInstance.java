package com.dailydealbd.network;

import com.dailydealbd.utils.ConstantsResources;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIInstance {
public static Retrofit retroInstance()
{
    return new Retrofit.Builder()
                   .baseUrl(ConstantsResources.BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
}

}
