package com.dailydealbd.viewmodel;

import android.app.Application;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.repositories.AccountRepository;


public class AccountViewModel extends AndroidViewModel {


    private AccountRepository repository;
    private LiveData<User> user;
    private OnClickRoutes.accountFragmentListener accountFragmentListener;



    public AccountViewModel(@NonNull Application application) {

        super(application);
        repository = new AccountRepository(application);
        user = repository.getUserInfo();
    }



    public void setAccountFragmentListener(OnClickRoutes.accountFragmentListener accountFragmentListener) {

        this.accountFragmentListener = accountFragmentListener;
    }



    public void gotoLogin()
    {
        accountFragmentListener.accountToLogin();
    }



    public LiveData<User> getUser() {

        return user;
    }


    public void signOutUser()
    {
        repository.signOutUser();
    }


}
