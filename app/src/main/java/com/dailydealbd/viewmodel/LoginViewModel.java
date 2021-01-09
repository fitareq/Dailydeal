package com.dailydealbd.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.dailydealbd.roomdata.model.Login;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.repositories.LoginRepository;


public class LoginViewModel extends AndroidViewModel {


    private LoginRepository repository;
    private OnClickRoutes.loginClickListener loginClickListener;
    public LoginViewModel(@NonNull Application application) {

        super(application);
        repository = new LoginRepository(application);
    }



    public void setLoginClickListener(OnClickRoutes.loginClickListener loginClickListener) {

        this.loginClickListener = loginClickListener;
    }



    public void authenticateUserFromRemote(Login login)
    {
        repository.authenticateUserFromRemote(login);
    }

    public User getUser()
    {
        return repository.getUser();
    }
    public  void gotoRegistration()
    {
        loginClickListener.loginToRegistration();
    }
}
