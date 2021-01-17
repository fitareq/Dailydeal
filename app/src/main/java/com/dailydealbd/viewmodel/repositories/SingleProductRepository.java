package com.dailydealbd.viewmodel.repositories;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.dailydealbd.network.APIInstance;
import com.dailydealbd.network.DailyDealApi;
import com.dailydealbd.roomdata.LocalDatabase;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.roomdata.model.WishList;
import com.dailydealbd.roomdata.model.dao.CartDao;
import com.dailydealbd.roomdata.model.dao.ProductsDao;
import com.dailydealbd.roomdata.model.dao.UserDao;
import com.dailydealbd.roomdata.model.dao.WishListDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SingleProductRepository
{
    private CartDao cartDao;
    private WishListDao wishListDao;
    private ProductsDao productsDao;
    private UserDao userDao;
    private final DailyDealApi api;

    private LiveData<Products> product;
    private LiveData<User> user;
    private final Application application;
    private LiveData<List<WishList>> wishlist;
    private LiveData<List<Cart>> carts;

    public SingleProductRepository(Application application)
    {

        this.application = application;
        LocalDatabase db = LocalDatabase.getINSTANCE(application);
        api = APIInstance.retroInstance().create(DailyDealApi.class);
        cartDao = db.cartDao();
        productsDao = db.productsDao();
        wishListDao = db.wishListDao();
        userDao = db.userDao();
        user = userDao.getCurrentUser();
        wishlist = wishListDao.getAllWishList();
        carts = cartDao.getCarts();
    }



    public LiveData<List<Cart>> getCarts() {

        return carts;
    }



    public LiveData<List<WishList>> getWishlist() {

        return wishlist;
    }



    public void deleteFromWishList(WishList wishList)
    {
        LocalDatabase.databaseWriteExecutors.execute(()->
        {
            wishListDao.deleteWishlist(wishList);
        });
    }
    public LiveData<User> getUser()
    {
        return this.user;
    }


    public void setProduct(String slug)
    {
        product = productsDao.getSingleProduct(slug);
    }
    public LiveData<Products> getProduct()
    {
        return this.product;
    }
    public void addProductToCart(Cart cart)
    {
        LocalDatabase.databaseWriteExecutors.execute(() -> cartDao.addProductToCart(cart));
    }
    void addToWishList(WishList wishList)
    {
        LocalDatabase.databaseWriteExecutors.execute(()->
        {
            wishListDao.insertWishlist(wishList);
        });
    }
    public void addProductToWishlist(WishList wishList){

        Call<WishList> call = api.addToWishList(wishList);
        call.enqueue(new Callback<WishList>() {
            @Override
            public void onResponse(Call<WishList> call, Response<WishList> response) {
                if (response.isSuccessful())
                {
                    addToWishList(wishList);
                    Toast.makeText(application.getApplicationContext(), "Added to WishList", Toast.LENGTH_LONG).show();
                }
            }



            @Override
            public void onFailure(Call<WishList> call, Throwable t) {

            }
        });
    }
}
