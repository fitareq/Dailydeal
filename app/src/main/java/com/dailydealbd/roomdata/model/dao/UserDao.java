package com.dailydealbd.roomdata.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.utils.ConstantsResources;


@Dao
public interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("DELETE FROM "+ConstantsResources.TABLE_LOGGED_IN_USER)
    void deleteUser();

    @Query("SELECT * FROM " + ConstantsResources.TABLE_LOGGED_IN_USER)
    LiveData<User> getCurrentUser();




}
