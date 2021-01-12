package com.dailydealbd.roomdata;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dailydealbd.roomdata.model.Banner;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.Categories;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.roomdata.model.Slider;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.roomdata.model.dao.BannerDao;
import com.dailydealbd.roomdata.model.dao.CartDao;
import com.dailydealbd.roomdata.model.dao.CategoriesDao;
import com.dailydealbd.roomdata.model.dao.ProductsDao;
import com.dailydealbd.roomdata.model.dao.SliderDao;
import com.dailydealbd.roomdata.model.dao.UserDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Slider.class, Products.class, Cart.class, Categories.class, Banner.class, User.class}, version = 1, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {


    public abstract SliderDao sliderDao();

    public abstract ProductsDao productsDao();

    public abstract CategoriesDao categoriesDao();

    public abstract BannerDao bannerDao();

    public abstract CartDao cartDao();

    public abstract UserDao userDao();



    private static volatile LocalDatabase INSTANCE;
    public static final int NUMBER_OF_THREAD = 6;
    public static final ExecutorService databaseWriteExecutors = Executors.newFixedThreadPool(NUMBER_OF_THREAD);



    public static LocalDatabase getINSTANCE(Application application) {

        if (INSTANCE == null) {
            synchronized (LocalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(application, LocalDatabase.class, "Dailydeal.db")
                                       .build();
                }
            }
        }
        return INSTANCE;
    }




}
