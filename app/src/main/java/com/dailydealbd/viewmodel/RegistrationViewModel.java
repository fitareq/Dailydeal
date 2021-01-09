package com.dailydealbd.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.view.Gravity;
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

    void registerNewUser(Registration registration) {

        repository.rUser(registration);
        showProgress();
    }



    private void showProgress() {

        String code = getCode();
        Toast.makeText(getApplication().getApplicationContext(), code, Toast.LENGTH_LONG).show();
    }



    public void userInputValidation(String name, String phone, String email, String password, String confirmPassword) {

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplication().getApplicationContext(), "Enter a name", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(getApplication().getApplicationContext(), "Enter a phone number", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplication().getApplicationContext(), "Enter an email", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplication().getApplicationContext(), "Enter a password", Toast.LENGTH_LONG).show();
        } else if (!TextUtils.equals(password, confirmPassword)) {
            Toast toast = new Toast(getApplication().getApplicationContext());
            toast.setGravity(Gravity.CENTER,0, 0);
            toast.setText("Password didn't match");
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
            //Toast.makeText(getApplication().getApplicationContext(), "Password didn't match", Toast.LENGTH_LONG).show();
        } else {
            Registration user = new Registration(name, email, password, phone);
            registerNewUser(user);
        }
    }



    public String getCode() {

        return repository.getCode();
    }




}
