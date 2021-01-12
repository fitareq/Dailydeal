package com.dailydealbd.viewmodel.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.LocalDatabase;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.roomdata.model.dao.UserDao;


public class AccountRepository
{
    private Application application;
    private UserDao userDao;
    private LiveData<User> user;
    public AccountRepository(Application application)
    {
        this.application = application;
        LocalDatabase db = LocalDatabase.getINSTANCE(application);
        userDao = db.userDao();
        user = userDao.getCurrentUser();
    }

    public LiveData<User> getUserInfo()
    {
        return user;
    }
    public void signOutUser()
    {
        LocalDatabase.databaseWriteExecutors.execute(()->
        {
            userDao.deleteUser();
        });
    }


}
