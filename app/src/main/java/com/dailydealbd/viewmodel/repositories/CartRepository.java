package com.dailydealbd.viewmodel.repositories;

import android.app.Application;
import android.view.Gravity;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.dailydealbd.network.APIInstance;
import com.dailydealbd.network.DailyDealApi;
import com.dailydealbd.roomdata.LocalDatabase;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.Order;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.roomdata.model.dao.CartDao;
import com.dailydealbd.roomdata.model.dao.UserDao;

import java.time.Duration;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartRepository {

    private CartDao cartDao;
    private UserDao userDao;
    private final DailyDealApi api;
    private LiveData<List<Cart>> allCarts;
    private LiveData<User> user;
    private Application application;
    public CartRepository(Application application)
    {

        this.application = application;
        LocalDatabase db = LocalDatabase.getINSTANCE(application);
        api = APIInstance.retroInstance().create(DailyDealApi.class);
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


    public void placeOrder(Order order)
    {
        Call<Order> call = api.checkoutProduct(order);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(application.getApplicationContext(), "Order Placed Successfully", Toast.LENGTH_LONG).show();
                    //orderFragmentListener.orderToCart();
                }else Toast.makeText(application.getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
            }



            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });
    }
    public interface orderPlacementResult
    {
        void sendResult(String result);
    }
}


