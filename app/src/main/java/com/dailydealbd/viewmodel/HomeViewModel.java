package com.dailydealbd.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.model.Categories;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.roomdata.model.Slider;
import com.dailydealbd.viewmodel.repositories.HomeRepository;

import java.util.List;


public class HomeViewModel extends AndroidViewModel {
private HomeRepository repository;
private LiveData<List<Slider>> sliderList;
private LiveData<List<Products>> productsList;
private LiveData<List<Categories>> categoriesList;


public HomeViewModel(@NonNull Application application) {
    super(application);
    this.repository = new HomeRepository(application);
    sliderList = repository.getSliderList();
    productsList = repository.getProductsList();
    categoriesList = repository.getCategoriesList();
}

public LiveData<List<Slider>> getSliderList()
{
    return this.sliderList;
}
public LiveData<List<Products>> getProductsList(){return this.productsList;}
public LiveData<List<Categories>> getCategoriesList(){return this.categoriesList;}
public void fetchSliderDataFromRemote()
{
    repository.fetchSliderDataFromRemote();
}
public void fetchProductsDataFromRemote()
{
    repository.fetchProductsDataFromRemote();
}
public void fetchCategoriesDataFromRemote()
{
    repository.fetchCategoriesDataFromRemote();
}






}
