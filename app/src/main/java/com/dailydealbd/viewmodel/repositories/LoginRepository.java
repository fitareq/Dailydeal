package com.dailydealbd.viewmodel.repositories;

import android.app.Application;
import android.widget.Toast;

import com.dailydealbd.network.APIInstance;
import com.dailydealbd.network.DailyDealApi;
import com.dailydealbd.roomdata.LocalDatabase;
import com.dailydealbd.roomdata.model.Login;
import com.dailydealbd.roomdata.model.User;
import com.dailydealbd.roomdata.model.dao.UserDao;
import com.dailydealbd.utils.OnClickRoutes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginRepository {
    private User user;
    private final DailyDealApi api;
    private UserDao userDao;
    private Application application;
    private OnClickRoutes.loginClickListener loginClickListener;
    public LoginRepository(Application application) {
        this.application = application;
        api = APIInstance.retroInstance().create(DailyDealApi.class);
        LocalDatabase db = LocalDatabase.getINSTANCE(application);
        userDao = db.userDao();
    }

    public void setLoginClickListener(OnClickRoutes.loginClickListener loginClickListener) {

        this.loginClickListener = loginClickListener;
    }

    public void authenticateUserFromRemote(Login login) {

        Call<User> call = api.loginUser(login);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful())
                    if (response.code()==201)
                    {
                        Toast.makeText(application.getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                        saveAuthenticatedUserToLocal(response.body());
                        loginClickListener.loginToHome();
                    }else Toast.makeText(application.getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
            }



            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void saveAuthenticatedUserToLocal(User user)
    {

        LocalDatabase.databaseWriteExecutors.execute(() -> userDao.insertUser(user));
    }
    public User getUser()
    {
        user = userDao.getCurrentUser();
        return user;
    }


}
