package com.dailydealbd.roomdata.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dailydealbd.roomdata.model.Categories;
import com.dailydealbd.utils.ConstantsResources;

import java.util.List;


@Dao
public interface CategoriesDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategoryList(List<Categories> categories);

    @Query("SELECT * FROM " + ConstantsResources.TABLE_CATEGORIES + " WHERE id LIKE :cId")
    Categories getSingleCategory(int cId);

    @Query("SELECT * FROM " + ConstantsResources.TABLE_CATEGORIES)
    LiveData<List<Categories>> getAllCategory();

    @Query("DELETE FROM " + ConstantsResources.TABLE_CATEGORIES)
    void deleteAllCategory();




}
