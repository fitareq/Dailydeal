package com.dailydealbd.viewmodel;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.aamarpay.library.AamarPay;
import com.aamarpay.library.DialogBuilder;
import com.dailydealbd.roomdata.model.Order;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.repositories.OrderRepository;

import org.json.JSONObject;


public class OrderViewModel extends AndroidViewModel {


    private OrderRepository repository;
    private LiveData<User> user;
    private OnClickRoutes.orderFragmentListener orderFragmentListener;
    public OrderViewModel(@NonNull Application application) {

        super(application);

        repository = new OrderRepository(application);
        user = repository.getUser();
    }



    public void setOrderFragmentListener(OnClickRoutes.orderFragmentListener orderFragmentListener) {

        this.orderFragmentListener = orderFragmentListener;
        repository.setOrderFragmentListener(orderFragmentListener);
    }



    public LiveData<User> getUser()
    {
        return user;
    }
    public void checkOut(Order order)
    {
        repository.placeOrder(order);
    }



}
