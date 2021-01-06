package com.dailydealbd.viewmodel.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.LocalDatabase;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.dao.CartDao;

import java.util.List;


public class CartRepository {

    private CartDao cartDao;
    private LiveData<List<Cart>> allCarts;
    public CartRepository(Application application)
    {

        LocalDatabase db = LocalDatabase.getINSTANCE(application);
        cartDao = db.cartDao();
        allCarts = cartDao.getCarts();
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


}
