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
    private OnClickRoutes.loginFragmentListener loginFragmentListener;
    public LoginViewModel(@NonNull Application application) {

        super(application);
        repository = new LoginRepository(application);
        user = repository.getUser();
    }



    public LiveData<User> getUser() {

        return user;
    }



    public void setLoginFragmentListener(OnClickRoutes.loginFragmentListener loginFragmentListener) {

        this.loginFragmentListener = loginFragmentListener;
        repository.setLoginFragmentListener(loginFragmentListener);
    }



    public void authenticateUserFromRemote(Login login)
    {
        repository.authenticateUserFromRemote(login);
    }


    public  void gotoRegistration()
    {
        loginFragmentListener.loginToRegistration();
    }
}
