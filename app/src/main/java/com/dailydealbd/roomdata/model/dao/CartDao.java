package com.dailydealbd.roomdata.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dailydealbd.roomdata.model.Cart;
import com.dailydealbd.utils.ConstantsResources;

import java.util.List;


@Dao
public interface CartDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
void addProductToCart(Cart cart);
@Delete
void deleteORCheckoutSingleCart(Cart cart);
@Query("DELETE FROM "+ ConstantsResources.TABLE_CART_USER)
void deleteORCheckoutAllCart();
@Query("SELECT * FROM "+ ConstantsResources.TABLE_CART_USER)
LiveData<List<Cart>> getCarts();

}
