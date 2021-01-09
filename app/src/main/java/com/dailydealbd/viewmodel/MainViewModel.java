package com.dailydealbd.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.dailydealbd.viewmodel.repositories.MainRepository;


public class MainViewModel extends AndroidViewModel {


    private MainRepository repository;
    public MainViewModel(@NonNull Application application) {

        super(application);
        repository = new MainRepository(application);
    }

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


}
