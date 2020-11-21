package com.example.ddd.Apis;

import com.example.ddd.Models.Order;
import com.example.ddd.Models.ali;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebServices {

    @GET("/get")
    Call<ali> get();
//
//    @GET("teams")
//    Call<NewsResponse> getNewsBySourceId(@Query("apiKey") String apiKey,
//                                         @Query("sources") String sources
//    );

    @POST("/order")
    Call<Order> CreateOrder(@Body Order order) ;


}