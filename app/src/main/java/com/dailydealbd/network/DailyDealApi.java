package com.dailydealbd.network;

import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.roomdata.model.Slider;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface DailyDealApi {


@GET("products")
Call<List<Products>> getAllProducts();

@GET("slider")
Call<List<Slider>> getAllSlider();




}