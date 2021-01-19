package com.dailydealbd.viewmodel.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.LocalDatabase;
import com.dailydealbd.roomdata.model.Banner;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.Order;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.roomdata.model.WishList;
import com.dailydealbd.roomdata.model.dao.BannerDao;
import com.dailydealbd.roomdata.model.dao.CartDao;
import com.dailydealbd.roomdata.model.dao.ProductsDao;
import com.dailydealbd.roomdata.model.dao.UserDao;
import com.dailydealbd.roomdata.model.dao.WishListDao;

import java.util.List;


public class AccountRepository {


    private Application application;
    private UserDao userDao;
    private LiveData<User> user;
    private ProductsDao productsDao;
    private BannerDao bannerDao;
    private CartDao cartDao;
    private WishListDao wishListDao;
    private LiveData<List<Products>> products;
    private LiveData<List<Banner>> banners;
    private LiveData<List<Cart>> carts;



    public AccountRepository(Application application) {

        this.application = application;
        LocalDatabase db = LocalDatabase.getINSTANCE(application);

        userDao = db.userDao();
        productsDao = db.productsDao();
        bannerDao = db.bannerDao();
        cartDao = db.cartDao();
        wishListDao = db.wishListDao();

        products = productsDao.getAllProducts();
        banners = bannerDao.getAllBanner();
        user = userDao.getCurrentUser();
        carts = cartDao.getCarts();
    }



    public LiveData<List<Cart>> getCarts() {

        return carts;
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
