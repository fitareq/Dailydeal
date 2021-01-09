package com.dailydealbd.viewmodel.repositories;

import android.app.Application;

import com.dailydealbd.roomdata.LocalDatabase;
import com.dailydealbd.roomdata.model.dao.UserDao;


public class AccountRepository
{
    private Application application;
    private UserDao userDao;
    public AccountRepository(Application application)
    {
        this.application = application;
        LocalDatabase db = LocalDatabase.getINSTANCE(application);
        userDao = db.userDao();
    }


}
