package com.dailydealbd.roomdata.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dailydealbd.roomdata.model.Products;
import com.dailydealbd.utils.ConstantsResources;

import java.util.List;


@Dao
public interface ProductsDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
void insertProductList(List<Products> products);

@Query("SELECT * FROM "+ ConstantsResources.TABLE_ALL_PRODUCT+" WHERE slug=:slug")
LiveData<Products> getSingleProduct(String slug);

@Query("SELECT * FROM "+ConstantsResources.TABLE_ALL_PRODUCT)
LiveData<List<Products>> getAllProducts();

@Query("DELETE FROM "+ConstantsResources.TABLE_ALL_PRODUCT)
void deleteAllProducts();

@Query("SELECT * FROM "+ConstantsResources.TABLE_ALL_PRODUCT+" WHERE toprated=1")
List<Products> getAllTopRatedProducts();

@Query("SELECT * FROM "+ConstantsResources.TABLE_ALL_PRODUCT+" WHERE week_deals=1")
List<Products> getAllWeekDealsProducts();

@Query("SELECT * FROM "+ConstantsResources.TABLE_ALL_PRODUCT+" WHERE category_id=:id")
List<Products> getCategoryWiseProducts(int id);

}
