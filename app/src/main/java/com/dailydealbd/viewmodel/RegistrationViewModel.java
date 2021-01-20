package com.dailydealbd.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.dailydealbd.roomdata.model.Registration;
import com.dailydealbd.utils.OnClickRoutes;
import com.dailydealbd.viewmodel.repositories.RegisterRepository;


public class RegistrationViewModel extends AndroidViewModel {


    private RegisterRepository repository;
    private OnClickRoutes.registrationClickListener registrationClickListener;



    public RegistrationViewModel(@NonNull Application application) {

        super(application);
        repository = new RegisterRepository(application);
    }


    public void gotoLogin()
    {
        registrationClickListener.goToLoginFromRegistration();
    }

    public void setRegistrationClickListener(OnClickRoutes.registrationClickListener registrationClickListener)
    {
        this.registrationClickListener = registrationClickListener;
        repository.setRegistrationClickListener(registrationClickListener);
    }

    public void registerNewUser(Registration registration) {

        repository.rUser(registration);
        showProgress();
    }



    private void showProgress() {

        String code = getCode();
        Toast.makeText(getApplication().getApplicationContext(), code, Toast.LENGTH_LONG).show();
    }







    public String getCode() {

        return repository.getCode();
    }




}
