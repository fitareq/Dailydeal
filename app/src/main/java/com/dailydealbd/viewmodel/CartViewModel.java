package com.dailydealbd.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.Order;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.roomdata.model.dao.CartDao;
import com.dailydealbd.viewmodel.repositories.CartRepository;

import java.util.List;


public class CartViewModel extends AndroidViewModel {

    private CartRepository repository;
    private LiveData<List<Cart>> allCart;
    private LiveData<User> user;
    public CartViewModel(@NonNull Application application) {
        super(application);
        repository = new CartRepository(application);
        allCart = repository.getAllCarts();
        user = repository.getUser();
    }

    public LiveData<List<Cart>> getAllCart()
    {
        return this.allCart;
    }
    public LiveData<User> getUser()
    {
        return this.user;
    }
    public void deleteCart(Cart cart)
    {
        repository.deleteCart(cart);
    }
    public void updateCart(Cart cart)
    {
        repository.updateCart(cart);
    }
    public void checkOut(Order order)
    {
        repository.placeOrder(order);
    }



}
