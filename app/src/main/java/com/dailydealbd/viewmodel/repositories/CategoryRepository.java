package com.dailydealbd.viewmodel.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.LocalDatabase;
import com.dailydealbd.roomdata.model.Categories;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.roomdata.model.dao.CategoriesDao;
import com.dailydealbd.roomdata.model.dao.ProductsDao;

import java.util.List;


public class CategoryRepository {


    private Application application;
    private LiveData<List<Products>> product;
    private LiveData<List<Categories>> categories;
    private CategoriesDao categoriesDao;
    private ProductsDao productsDao;



    public CategoryRepository(Application application) {

        this.application = application;
        LocalDatabase db = LocalDatabase.getINSTANCE(application);
        categoriesDao = db.categoriesDao();
        productsDao = db.productsDao();
        product = productsDao.getAllProducts();
        categories = categoriesDao.getAllCategory();
    }



    public LiveData<List<Categories>> getCategories() {

        return categories;
    }



    public LiveData<List<Products>> getProduct() {

        return product;
    }




}
