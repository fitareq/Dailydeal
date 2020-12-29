package com.dailydealbd.viewmodel.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.model.Slider;
import com.dailydealbd.roomdata.model.dao.SliderDao;
import com.dailydealbd.network.APIInstance;
import com.dailydealbd.network.DailyDealApi;
import com.dailydealbd.roomdata.LocalDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeRepository
{

private DailyDealApi api;
private LiveData<List<Slider>> sliderList;
private LocalDatabase db;
private SliderDao sliderDao;

public HomeRepository(Application application) {

    api = APIInstance.retroInstance().create(DailyDealApi.class);
    db = LocalDatabase.getINSTANCE(application);
    sliderDao = db.sliderDao();
    sliderList = sliderDao.getAllSlider();
}


public void fetchDataFromRemote()
{

    Call<List<Slider>> call = api.getAllSlider();
    call.enqueue(new Callback<List<Slider>>() {
        @Override
        public void onResponse(Call<List<Slider>> call, Response<List<Slider>> response) {
            if (response.isSuccessful())
            {
                insertSliderList(response.body());
            }
        }



        @Override
        public void onFailure(Call<List<Slider>> call, Throwable t) {
        }
    });
}



public LiveData<List<Slider>> getSliderList() {

    sliderList = sliderDao.getAllSlider();
    return sliderList;
}



private void insertSliderList(List<Slider> slider) {
    LocalDatabase.databaseWriteExecutors.execute(new Runnable() {
        @Override
        public void run() {
            sliderDao.insertSliderList(slider);
        }
    });
}




}
