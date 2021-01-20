package com.dailydealbd.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.model.Banner;
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
private LiveData<List<Banner>> bannerList;


public HomeViewModel(@NonNull Application application) {
    super(application);
    this.repository = new HomeRepository(application);
    sliderList = repository.getSliderList();
    productsList = repository.getProductsList();
    categoriesList = repository.getCategoriesList();
    bannerList = repository.getBannerList();
}

public LiveData<List<Slider>> getSliderList()
{
    return this.sliderList;
}
public LiveData<List<Products>> getProductsList(){return this.productsList;}
public LiveData<List<Categories>> getCategoriesList(){return this.categoriesList;}
public LiveData<List<Banner>> getBannerList(){return this.bannerList;}
public void fetchBannerDataFromRemote(){repository.fetchBannerDataFromRemote();}
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
public LiveData<Products> getProducts(String slug){
    return repository.getProducts(slug); }






}
