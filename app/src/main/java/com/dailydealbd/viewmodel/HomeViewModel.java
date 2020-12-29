package com.dailydealbd.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.roomdata.model.Slider;
import com.dailydealbd.viewmodel.repositories.HomeRepository;

import java.util.List;


public class HomeViewModel extends AndroidViewModel {
private HomeRepository repository;
private LiveData<List<Slider>> sliderList;
private LiveData<List<Products>> productsList;


public HomeViewModel(@NonNull Application application) {
    super(application);
    this.repository = new HomeRepository(application);
    sliderList = repository.getSliderList();
    productsList = repository.getProductsList();
}

public LiveData<List<Slider>> getSliderList()
{
    return this.sliderList;
}
public LiveData<List<Products>> getProductsList(){return this.productsList;}
public void fetchSliderDataFromRemote()
{
    repository.fetchSliderDataFromRemote();
}
public void fetchProductsDataFromRemote()
{
    repository.fetchProductsDataFromRemote();
}






}
