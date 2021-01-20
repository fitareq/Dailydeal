package com.dailydealbd.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.model.Categories;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.viewmodel.repositories.CategoryRepository;

import java.util.List;


public class CategoryViewModel extends AndroidViewModel {


    private CategoryRepository repository;
    private LiveData<List<Categories>> categories;
    private LiveData<List<Products>> products;
    public CategoryViewModel(@NonNull Application application) {

        super(application);
        repository = new CategoryRepository(application);
        categories = repository.getCategories();
        products = repository.getProduct();
    }



    public LiveData<List<Categories>> getCategories() {

        return categories;
    }



    public LiveData<List<Products>> getProducts() {

        return products;
    }




}
