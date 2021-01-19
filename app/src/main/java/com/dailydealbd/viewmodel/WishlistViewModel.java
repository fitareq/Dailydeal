package com.dailydealbd.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.model.WishList;
import com.dailydealbd.viewmodel.repositories.WishlistRepository;

import java.util.List;


public class WishlistViewModel extends AndroidViewModel {


    private WishlistRepository repository;
    private LiveData<List<WishList>> wishList;



    public WishlistViewModel(@NonNull Application application) {

        super(application);
        repository = new WishlistRepository(application);
        wishList = repository.getWishList();
    }



    public LiveData<List<WishList>> getWishList() {

        return wishList;
    }



    public void deleteWishlist(WishList wishList) {

        repository.deleteWishlist(wishList);
    }




}
