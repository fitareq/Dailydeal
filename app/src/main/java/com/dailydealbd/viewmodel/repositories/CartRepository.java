package com.dailydealbd.viewmodel.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.LocalDatabase;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.roomdata.model.dao.CartDao;
import com.dailydealbd.roomdata.model.dao.UserDao;

import java.util.List;


public class CartRepository {

    private CartDao cartDao;
    private UserDao userDao;
    private LiveData<List<Cart>> allCarts;
    private LiveData<User> user;
    public CartRepository(Application application)
    {

        LocalDatabase db = LocalDatabase.getINSTANCE(application);
        cartDao = db.cartDao();
        userDao = db.userDao();
        allCarts = cartDao.getCarts();
        user = userDao.getCurrentUser();
    }
    public void deleteCart(Cart cart)
    {
        LocalDatabase.databaseWriteExecutors.execute(()->
        {
            cartDao.deleteORCheckoutSingleCart(cart);
        });
    }
    public LiveData<List<Cart>> getAllCarts()
    {
        return this.allCarts;
    }
    public LiveData<User> getUser(){return this.user;}
    public void updateCart(Cart cart)
    {
        LocalDatabase.databaseWriteExecutors.execute(() -> {
            cartDao.addProductToCart(cart);
        });

    }
    public void subCartQuantity(int quantity)
    {

    }


}
