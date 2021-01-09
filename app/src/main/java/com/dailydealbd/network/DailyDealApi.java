package com.dailydealbd.network;

import com.dailydealbd.roomdata.model.Banner;
import com.dailydealbd.roomdata.model.Categories;
import com.dailydealbd.roomdata.model.Login;
import com.dailydealbd.roomdata.model.Order;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.roomdata.model.Registration;
import com.dailydealbd.roomdata.model.Slider;
import com.dailydealbd.roomdata.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface DailyDealApi {


    @GET("products")
    Call<List<Products>> getAllProducts();

    @GET("slider")
    Call<List<Slider>> getAllSlider();

    @GET("categoryAll")
    Call<List<Categories>> getAllCategories();

    @GET("banner")
    Call<List<Banner>> getAllBanner();

    @GET("product/{slug}")
    Call<Products> getSingleProduct(@Path("slug") String slug);

    @POST("register")
    Call<Registration> registerNewUser(@Body Registration registration);

    @POST("login")
    Call<User> loginUser(@Body Login login);

    @POST("checkout")
    Call<Order> checkoutProduct(@Body Order checkOut);




}
