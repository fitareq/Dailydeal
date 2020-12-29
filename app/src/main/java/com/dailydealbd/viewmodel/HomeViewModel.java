package com.dailydealbd.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dailydealbd.roomdata.model.Slider;
import com.dailydealbd.viewmodel.repositories.HomeRepository;

import java.util.List;


public class HomeViewModel extends AndroidViewModel {
private HomeRepository repository;
private LiveData<List<Slider>> sliderList;

public HomeViewModel(@NonNull Application application) {
    super(application);
    this.repository = new HomeRepository(application);
    sliderList = repository.getSliderList();
}

public LiveData<List<Slider>> getSliderList()
{
    return this.sliderList;
}
public void fetchDataFromRemote()
{
    repository.fetchDataFromRemote();
}





}
