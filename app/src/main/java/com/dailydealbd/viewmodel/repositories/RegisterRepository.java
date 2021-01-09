package com.dailydealbd.viewmodel.repositories;

import android.app.Application;
import android.widget.Toast;

import com.dailydealbd.network.APIInstance;
import com.dailydealbd.network.DailyDealApi;
import com.dailydealbd.roomdata.model.Registration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterRepository
{
    private final DailyDealApi api;
    private final Application application;
    private String code;

    public RegisterRepository(Application application)
    {
        api = APIInstance.retroInstance().create(DailyDealApi.class);
        this.application = application;
    }



    public void rUser(Registration registration){registerNewUser(registration);}
    void registerNewUser(Registration registration)
    {

        Call<Registration> call = api.registerNewUser(registration);
        call.enqueue(new Callback<Registration>() {
            @Override
            public void onResponse(Call<Registration> call, Response<Registration> response) {
                if (response.isSuccessful()) {
                    setCode(response.message());
                }
            }
            @Override
            public void onFailure(Call<Registration> call, Throwable t) {

            }
        });
    }



    private void setCode(String code) {

        this.code = code;
    }
    public String getCode()
    {
        return this.code;
    }




}
