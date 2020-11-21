package com.example.ddd.Apis;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static Retrofit retrofit;

    private static Retrofit getInstance(){
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://a0fdf88f-ce28-41b4-a2a9-ae20cb277c78.mock.pstmn.io")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

    public static WebServices getApis(){
        return getInstance().create(WebServices.class);
    }
}
