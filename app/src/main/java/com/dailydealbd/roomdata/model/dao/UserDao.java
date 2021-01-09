package com.dailydealbd.roomdata.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.utils.ConstantsResources;


@Dao
public interface UserDao {


    @Insert
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM " + ConstantsResources.TABLE_LOGGED_IN_USER)
    User getCurrentUser();




}
