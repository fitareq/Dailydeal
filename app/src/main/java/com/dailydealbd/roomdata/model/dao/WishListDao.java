package com.dailydealbd.roomdata.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dailydealbd.roomdata.model.WishList;
import com.dailydealbd.utils.ConstantsResources;

import java.util.List;


@Dao
public interface WishListDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWishlist(WishList wishList);

    @Delete
    void deleteWishlist(WishList wishList);

    @Query("SELECT * FROM "+ ConstantsResources.TABLE_WISHLIST)
    LiveData<List<WishList>> getAllWishList();

    @Query("SELECT * FROM "+ConstantsResources.TABLE_WISHLIST+" WHERE pid =:pid")
    LiveData<WishList> isProductWishListed(int pid);
}
