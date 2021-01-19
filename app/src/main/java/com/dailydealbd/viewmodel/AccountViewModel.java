package com.dailydealbd.viewmodel;

import android.app.Application;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.model.Banner;
import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.repositories.AccountRepository;

import java.util.List;


public class AccountViewModel extends AndroidViewModel {


    private AccountRepository repository;
    private LiveData<User> user;
    private LiveData<List<Products>> products;
    private LiveData<List<Banner>> banners;
    private LiveData<List<Cart>> carts;
    private OnClickRoutes.accountFragmentListener accountFragmentListener;



    public AccountViewModel(@NonNull Application application) {

        super(application);
        repository = new AccountRepository(application);
        user = repository.getUserInfo();
        products = repository.getProducts();
        banners = repository.getBanners();
        carts = repository.getCarts();
    }



    public LiveData<List<Cart>> getCarts() {

        return carts;
    }



    public LiveData<List<Products>> getProducts() {

        return products;
    }



    public LiveData<List<Banner>> getBanners() {

        return banners;
    }



    public void setAccountFragmentListener(OnClickRoutes.accountFragmentListener accountFragmentListener) {

        this.accountFragmentListener = accountFragmentListener;
    }



    public void gotoLogin()
    {
        accountFragmentListener.accountToLogin();
    }



    public LiveData<User> getUser() {

        return user;
    }


    public void signOutUser()
    {
        repository.signOutUser();
    }


}
