package com.dailydealbd.viewmodel.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.LocalDatabase;
import com.dailydealbd.roomdata.model.Banner;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.roomdata.model.dao.BannerDao;
import com.dailydealbd.roomdata.model.dao.ProductsDao;
import com.dailydealbd.roomdata.model.dao.UserDao;

import java.util.List;


public class AccountRepository {


    private Application application;
    private UserDao userDao;
    private LiveData<User> user;
    private ProductsDao productsDao;
    private BannerDao bannerDao;
    private LiveData<List<Products>> products;
    private LiveData<List<Banner>> banners;



    public AccountRepository(Application application) {

        this.application = application;
        LocalDatabase db = LocalDatabase.getINSTANCE(application);
        userDao = db.userDao();
        user = userDao.getCurrentUser();
        productsDao = db.productsDao();
        bannerDao = db.bannerDao();
        products = productsDao.getAllProducts();
        banners = bannerDao.getAllBanner();
    }



    public LiveData<List<Banner>> getBanners() {

        return banners;
    }



    public LiveData<List<Products>> getProducts() {

        return products;
    }



    public LiveData<User> getUserInfo() {

        return user;
    }



    public void signOutUser() {

        LocalDatabase.databaseWriteExecutors.execute(() ->
        {
            userDao.deleteUser();
        });
    }




}
