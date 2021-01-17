package com.dailydealbd.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.roomdata.model.WishList;
import com.dailydealbd.viewmodel.repositories.SingleProductRepository;

import java.util.List;


public class SingleProductViewModel extends AndroidViewModel {


    private SingleProductRepository repository;
    private LiveData<Products> product;
    private LiveData<User> user;
    private LiveData<List<WishList>> wishlist;
    private LiveData<List<Cart>> carts;
    public SingleProductViewModel(@NonNull Application application) {

        super(application);
        repository = new SingleProductRepository(application);
        user = repository.getUser();
        wishlist = repository.getWishlist();
        carts = repository.getCarts();
    }



    public LiveData<List<Cart>> getCarts() {

        return carts;
    }



    public LiveData<List<WishList>> getWishlist() {

        return wishlist;
    }



    public void setProduct(String slug)
    {
        repository.setProduct(slug);
    }
    public LiveData<Products> getProduct()
    {
        product = repository.getProduct();
        return this.product;
    }
    public LiveData<User> getUser()
    {
        return this.user;
    }
    public void addToCart(Cart cart)
    {
        repository.addProductToCart(cart);
    }
    public void addToWishlist(WishList wishList){ repository.addProductToWishlist(wishList);}

    public void deleteFromWishList(WishList wishList)
    {
        repository.deleteFromWishList(wishList);
    }



}
