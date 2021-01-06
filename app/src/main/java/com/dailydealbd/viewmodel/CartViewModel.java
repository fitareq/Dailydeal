package com.dailydealbd.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.dao.CartDao;
import com.dailydealbd.viewmodel.repositories.CartRepository;

import java.util.List;


public class CartViewModel extends AndroidViewModel {

    private CartRepository repository;
    private LiveData<List<Cart>> allCart;
    public CartViewModel(@NonNull Application application) {
        super(application);
        repository = new CartRepository(application);
        allCart = repository.getAllCarts();
    }

    public LiveData<List<Cart>> getAllCart()
    {
        return this.allCart;
    }
    public void deleteCart(Cart cart)
    {
        repository.deleteCart(cart);
    }



}
