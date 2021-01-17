package com.dailydealbd.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dailydealbd.R;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.utils.ConstantsResources;
import com.dailydealbd.viewmodel.repositories.MainRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class MainViewModel extends AndroidViewModel {


    private MainRepository repository;
    private LiveData<User> user;
    private LiveData<List<Cart>> carts;


    public MainViewModel(@NonNull Application application) {

        super(application);
        repository = new MainRepository(application);
        user = repository.getUser();
        carts = repository.getCarts();
    }



    public LiveData<List<Cart>> getCarts() {

        return carts;
    }



    public LiveData<User> getUser() {

        return user;
    }



    public void fetchBannerDataFromRemote() {

        repository.fetchBannerDataFromRemote();
    }



    public void fetchSliderDataFromRemote() {

        repository.fetchSliderDataFromRemote();
    }



    public void fetchProductsDataFromRemote() {

        repository.fetchProductsDataFromRemote();
    }



    public void fetchCategoriesDataFromRemote() {

        repository.fetchCategoriesDataFromRemote();
    }












}
