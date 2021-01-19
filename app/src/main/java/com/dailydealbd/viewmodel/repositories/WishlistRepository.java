package com.dailydealbd.viewmodel.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.LocalDatabase;
import com.dailydealbd.roomdata.model.WishList;
import com.dailydealbd.roomdata.model.dao.WishListDao;

import java.util.List;


public class WishlistRepository {


    private Application application;
    private WishListDao wishListDao;
    private LiveData<List<WishList>> wishList;



    public WishlistRepository(Application application) {

        this.application = application;
        LocalDatabase db = LocalDatabase.getINSTANCE(application);
        wishListDao = db.wishListDao();
        wishList = wishListDao.getAllWishList();
    }



    public LiveData<List<WishList>> getWishList() {

        return wishList;
    }



    public void deleteWishlist(WishList wishList) {

        LocalDatabase.databaseWriteExecutors.execute(() ->
        {
            wishListDao.deleteWishlist(wishList);
        });

    }




}
