package com.dailydealbd.viewmodel.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.LocalDatabase;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.roomdata.model.dao.CartDao;
import com.dailydealbd.roomdata.model.dao.ProductsDao;


public class SingleProductRepository
{
    private CartDao cartDao;
    private ProductsDao productsDao;

    private LiveData<Products> product;

    public SingleProductRepository(Application application)
    {

        LocalDatabase db = LocalDatabase.getINSTANCE(application);
        cartDao = db.cartDao();
        productsDao = db.productsDao();
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
}
