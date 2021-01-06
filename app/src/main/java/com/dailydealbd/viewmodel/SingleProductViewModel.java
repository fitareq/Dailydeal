package com.dailydealbd.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.viewmodel.repositories.SingleProductRepository;


public class SingleProductViewModel extends AndroidViewModel {


    private SingleProductRepository repository;
    private LiveData<Products> product;
    public SingleProductViewModel(@NonNull Application application) {

        super(application);
        repository = new SingleProductRepository(application);
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
    public void addToCart(Cart cart)
    {
        repository.addProductToCart(cart);
    }



}
