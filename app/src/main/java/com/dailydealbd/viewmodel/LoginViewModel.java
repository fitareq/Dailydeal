package com.dailydealbd.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.model.Login;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.repositories.LoginRepository;


public class LoginViewModel extends AndroidViewModel {


    private LoginRepository repository;
    private LiveData<User> user;
    private OnClickRoutes.loginClickListener loginClickListener;
    public LoginViewModel(@NonNull Application application) {

        super(application);
        repository = new LoginRepository(application);
        user = repository.getUser();
    }



    public LiveData<User> getUser() {

        return user;
    }



    public void setLoginClickListener(OnClickRoutes.loginClickListener loginClickListener) {

        this.loginClickListener = loginClickListener;
        repository.setLoginClickListener(loginClickListener);
    }



    public void authenticateUserFromRemote(Login login)
    {
        repository.authenticateUserFromRemote(login);
    }


    public  void gotoRegistration()
    {
        loginClickListener.loginToRegistration();
    }
}
